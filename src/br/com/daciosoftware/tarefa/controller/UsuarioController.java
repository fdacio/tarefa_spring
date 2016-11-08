package br.com.daciosoftware.tarefa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.daciosoftware.tarefa.Util;
import br.com.daciosoftware.tarefa.dao.JpaCategoriaDao;
import br.com.daciosoftware.tarefa.dao.JpaUsuarioDao;
import br.com.daciosoftware.tarefa.model.AlteraSenha;
import br.com.daciosoftware.tarefa.model.Login;
import br.com.daciosoftware.tarefa.model.Usuario;

@Controller // -> Indica que o spring irá controlar essa classe
@Transactional // -> Para todos os métodos serem controlados por transações JPA
public class UsuarioController {

	private JpaCategoriaDao daoCategoria;
	private LoginController login;
	private JpaUsuarioDao dao;

	@Autowired
	public UsuarioController(JpaUsuarioDao dao, JpaCategoriaDao daoCategoria, LoginController login) {
		this.dao = dao;
		this.daoCategoria = daoCategoria;
		this.login = login;
	}

	@RequestMapping("cadastrese")
	public String primeiroUsuario(Model model) {
		boolean primeiro = (dao.lista().size() == 0) ? true : false;
		model.addAttribute("primeiro", primeiro);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("categorias", daoCategoria.lista());
		model.addAttribute("login", new Login());
		return "usuario/cadastrese";
	}

	@RequestMapping(value = "cadastraUsuario", method = RequestMethod.POST)
	public String cadastraUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
		
		model.addAttribute("login", new Login());
		model.addAttribute("categorias", daoCategoria.lista());

		if(usuario.getCategoria().getId() == 0 || usuario.getCategoria().getId().equals("")){
			result.rejectValue("categoria","error.usuario","Categoria é obrigatória");
		}

		if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			result.rejectValue("confirmaSenha","error.usuario","Confirmação de senha inválida");
		}
		
		System.out.println(result.getAllErrors().toString());
		
		if (result.hasErrors()) {
			return "usuario/cadastrese";
		}

		usuario.setSenha(Util.criptografaSenha(usuario.getSenha()));
		dao.adiciona(usuario);
		model.addAttribute("msgSucesso", "Cadastro realizado com sucesso!");
		model.addAttribute("usuario", new Usuario());
		return "forward:cadastrese";
	}

	@RequestMapping("dadosUsuario")
	public String alteraUsuario(Model model) {
		Usuario usuario = login.getUsuarioLogado();
		model.addAttribute("categorias", daoCategoria.lista());
		model.addAttribute("usuario", dao.buscaPorId(usuario.getId()));
		return "usuario/edita";
	}

	@RequestMapping(value = "gravaDados", method = RequestMethod.POST)
	public String gravaUsuario(@Valid Usuario usuario, BindingResult result, Model model) {

		model.addAttribute("login", new Login());
		model.addAttribute("categorias", daoCategoria.lista());

		if(usuario.getCategoria().getId() == 0 || usuario.getCategoria().getId().equals("")){
			result.rejectValue("categoria", "error.usuario", "Categoria é obrigatória");
		}

		if (result.hasErrors()) {
			return "usuario/edita";
		}

		String senha = dao.buscaPorId(usuario.getId()).getSenha();
		usuario.setSenha(senha);
		dao.altera(usuario);
		model.addAttribute("msgSucesso", "Operação realizada com sucesso!");
		return "usuario/edita";
	}

	@RequestMapping("alteraSenha")
	public String alteraSenha(Model model) {
		model.addAttribute("alteraSenha", new AlteraSenha());
		return "usuario/senha";
	}

	
	@RequestMapping(value = "gravaSenha", method = RequestMethod.POST)
	public String gravaSenha(@Valid AlteraSenha alteraSenha, BindingResult result, Model model) {

		if (!alteraSenha.getNovaSenha().equals(alteraSenha.getConfirmaNovaSenha())) {
			result.rejectValue("confirmaNovaSenha", "error.alteraSenha", "Confirmação de senha inválida");		
		}

		if (result.hasErrors()) {
			return "usuario/senha";
		}


		Usuario usuarioLogado = login.getUsuarioLogado();
		usuarioLogado.setSenha(Util.criptografaSenha(alteraSenha.getNovaSenha()));
		dao.altera(usuarioLogado);
		model.addAttribute("msgSucesso", "Operação realizada com sucesso!");
		return "usuario/senha";
	}

	@RequestMapping("bloquearUsuario")
	public String bloquearUsuario(Integer id, Model model) {
		Usuario usuarioLogado = login.getUsuarioLogado();
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
		Usuario usuarioLogado = login.getUsuarioLogado();
		if (usuarioLogado.isAdministrador()) {
			List<Usuario> usuarios = dao.lista();
			model.addAttribute("usuarios", usuarios);
			return "usuario/lista";
		} else {
			return "goLogout";
		}
	}

}
