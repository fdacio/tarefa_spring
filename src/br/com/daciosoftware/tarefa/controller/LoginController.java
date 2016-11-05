package br.com.daciosoftware.tarefa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.daciosoftware.tarefa.dao.JpaUsuarioDao;
import br.com.daciosoftware.tarefa.model.Usuario;

@Controller
public class LoginController {

	private JpaUsuarioDao dao;
	
	@Autowired
	public LoginController(JpaUsuarioDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST) 
	public String login(Usuario usuario, HttpSession session, Model model) { 
		Usuario usuarioLogin = dao.existeUsuario(usuario);
		if(usuarioLogin != null) { 
			session.setAttribute("usuarioLogado", usuarioLogin); 
			model.addAttribute("usuarioLogado", usuarioLogin);
			return "redirect:wellcome"; 
		}
		else{
			model.addAttribute("mensagemLogin","Login Inválido!");
			return "index";
		}
		
	}
	
	@RequestMapping("logout") 
	public String logout(HttpSession session) { 
		session.invalidate(); 
		return "redirect:index"; 
	}
	
	@RequestMapping("wellcome") 
	public String wellcome() { 
		return "wellcome"; 
	}
	
}
