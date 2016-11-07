package br.com.daciosoftware.tarefa.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.daciosoftware.tarefa.dao.JpaUsuarioDao;
import br.com.daciosoftware.tarefa.model.Login;
import br.com.daciosoftware.tarefa.model.Usuario;

@Controller
public class LoginController {

	private JpaUsuarioDao dao;

	@Autowired
	public LoginController(JpaUsuarioDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "goLogin", method = RequestMethod.POST)
	public String goLogin(@Valid Login login, BindingResult result, Model model) {
		
		if(result.hasFieldErrors("email")){
			return "index";
		}
		
		if(result.hasFieldErrors("senha")){
			return "index";
		}

		Usuario usuarioLogin = dao.getUsuarioLogin(login);
		if (usuarioLogin != null) {
			if (!usuarioLogin.isBloqueado()) {
				setUsuarioLogado(usuarioLogin);
				//session.setAttribute("usuarioLogado", usuarioLogin);
				model.addAttribute("usuarioLogado", usuarioLogin);
				return "redirect:wellcome";
			}else{
				model.addAttribute("mensagemLogin", "Usuário bloqueado!");
				return "index";
			}
		} else {
			model.addAttribute("mensagemLogin", "Login Inválido!");
			return "index";
		}

	}

	@RequestMapping("goLogout")
	public String goLogout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}

	@RequestMapping("wellcome")
	public String wellcome() {
		return "wellcome";
	}

	private HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); 
	}	

	public Usuario getUsuarioLogado(){
		return ((Usuario) session().getAttribute("usuarioLogado"));
	}

	public void setUsuarioLogado(Usuario usuario){
		session().setAttribute("usuarioLogado", usuario);
	}
	
}
