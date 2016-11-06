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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.daciosoftware.tarefa.Util;
import br.com.daciosoftware.tarefa.dao.JpaCategoriaDao;
import br.com.daciosoftware.tarefa.dao.JpaUsuarioDao;
import br.com.daciosoftware.tarefa.model.AlteraSenha;
import br.com.daciosoftware.tarefa.model.Login;
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
		model.addAttribute("login", new Login());
		model.addAttribute("categorias", daoCatergoria.lista());
		return "usuario/cadastrese";
	}

	@RequestMapping(value = "cadastraUsuario", method = RequestMethod.POST)
	public String cadastraUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
		model.addAttribute("login", new Login());
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
	
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); 
	}	

	private Usuario getUsuarioLogado(){
		return ((Usuario) session().getAttribute("usuarioLogado"));
	}
	
	@RequestMapping("dadosUsuario")
	public String alteraUsuario(Model model) {
		Usuario usuario = getUsuarioLogado();
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
	public String alteraSenha(Model model) {
		model.addAttribute("alteraSenha", new AlteraSenha());
		return "usuario/senha";
	}

	@RequestMapping(value = "gravaSenha", method = RequestMethod.POST)
	public String gravaSenha(@Valid AlteraSenha alteraSenha, BindingResult result, HttpSession session, Model model) {

		if (result.hasErrors()) {
			return "usuario/senha";
		}

		if (!alteraSenha.getNovaSenha().equals(alteraSenha.getConfirmaNovaSenha())) {
			model.addAttribute("mensagemSenha", "Confirmação de senha inválida!");
			return "usuario/senha";
		}

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario.setSenha(Util.criptografaSenha(alteraSenha.getNovaSenha()));
		dao.altera(usuario);
		model.addAttribute("mensagemSenhaSucesso", "Operação realizada com sucesso!");
		return "usuario/senha";
	}

	@RequestMapping("bloquearUsuario")
	public String bloquearUsuario(Integer id, Model model) {
		Usuario usuarioLogado = getUsuarioLogado();
		if (usuarioLogado.isAdministrador()) {
			Usuario usuario = dao.buscaPorId(id);
			boolean bloqueado = usuario.isBloqueado();
			usuario.setBloqueado(!bloqueado);
			dao.altera(usuario);
			return "redirect:todosUsuarios";

		} else {
			return "goLogout";
		}
		
	}

	@RequestMapping("todosUsuarios")
	public String listaUsuario(Model model) {
		Usuario usuarioLogado = getUsuarioLogado();
		if (usuarioLogado.isAdministrador()) {
			List<Usuario> usuarios = dao.lista();
			model.addAttribute("usuarios", usuarios);
			return "usuario/lista";
		}else{
			return "goLogout";
		}
	}

}
