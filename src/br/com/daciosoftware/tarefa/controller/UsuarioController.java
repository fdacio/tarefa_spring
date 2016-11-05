package br.com.daciosoftware.tarefa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.daciosoftware.tarefa.Util;
import br.com.daciosoftware.tarefa.dao.JpaCategoriaDao;
import br.com.daciosoftware.tarefa.dao.JpaUsuarioDao;
import br.com.daciosoftware.tarefa.model.Usuario;

@Controller // -> Indica que o spring irá controlar essa classe
@Transactional // -> Para todos os métodos serem controlados por transações JPA
public class UsuarioController {

	private JpaUsuarioDao dao;

	@Autowired
	private JpaCategoriaDao daoCatergoria;
	
	@Autowired
	public UsuarioController(JpaUsuarioDao dao) {
		this.dao = dao;
	}

	@RequestMapping("cadastrese")
	public String primeiroUsuario(Model model) {
		boolean primeiro = (dao.lista().size() == 0) ? true : false;
		model.addAttribute("primeiro", primeiro);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("categorias", daoCatergoria.lista());
		return "usuario/cadastrese";
	}

	@RequestMapping(value = "cadastraUsuario", method = RequestMethod.POST)
	public String cadastraUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("categorias", daoCatergoria.lista());
			return "usuario/cadastrese";
		}
		
		if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			model.addAttribute("confirmaSenha", "Confirmação de senha inválida");
			return "usuario/cadastrese";
		}
		
		usuario.setSenha(Util.criptografaSenha(usuario.getSenha()));
		dao.adiciona(usuario);
		model.addAttribute("msgSucesso", "Cadastro realizado com sucesso!");
		model.addAttribute("usuario", new Usuario());
		return "forward:cadastrese";
	}

	@RequestMapping("dadosUsuario")
	public String alteraUsuario(Model model, HttpSession session) {
		Usuario usuario = ((Usuario) session.getAttribute("usuarioLogado"));
		model.addAttribute("categorias", daoCatergoria.lista());
		model.addAttribute("usuario", dao.buscaPorId(usuario.getId()));
		return "usuario/edita";
	}

	
	@RequestMapping(value = "gravaDados", method = RequestMethod.POST)
	public String gravaUsuario(@Valid Usuario usuario, BindingResult result, Model model, HttpSession session) {

		model.addAttribute("categorias", daoCatergoria.lista());
		
		if (result.hasErrors()) {
			return "usuario/edita";
		}
		String senha = dao.buscaPorId(usuario.getId()).getSenha();
		usuario.setSenha(senha);
		dao.altera(usuario);
		model.addAttribute("mensagemSucesso", "Operação realizada com sucesso!");
		return "usuario/edita";
	}
	

	@RequestMapping("alteraSenha")
	public String alteraSenha() {
		return "usuario/senha";
	}

	@RequestMapping(value = "gravaSenha", method = RequestMethod.POST)
	public String gravaSenha(@RequestParam("nova_senha") String novaSenha,
			@RequestParam("confirma_senha") String confirmaSenha, HttpSession session, Model model) {

		if (novaSenha.length() < 6) {
			model.addAttribute("mensagemSenha", "A senha tem que ter mínimo 6 carecteres!");
			return "usuario/senha";
		}

		if (!novaSenha.equals(confirmaSenha)) {
			model.addAttribute("mensagemSenha", "Confirmação de senha inválida!");
			return "usuario/senha";
		}

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario.setSenha(Util.criptografaSenha(novaSenha));
		dao.altera(usuario);
		model.addAttribute("mensagem", "Operação realizada com sucesso!");
		return "usuario/mensagem";
	}

	@RequestMapping("excluiUsuario")
	public String existeUsuario(Integer id, Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado.isAdministrador()) {
			dao.remove(dao.buscaPorId(id));
			model.addAttribute("mensagem", "Operação realizada com sucesso!");

		} else {
			model.addAttribute("mensagem", "Usuário não tem autorização!");
		}
		return "usuario/mensagem";
	}

	@RequestMapping("todosUsuarios")
	public String listaUsuario(Model model) {
		List<Usuario> usuarios = dao.lista();
		model.addAttribute("usuarios", usuarios);
		return "usuario/lista";
	}

}
