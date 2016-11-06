package br.com.daciosoftware.tarefa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.daciosoftware.tarefa.model.Login;

@Controller
public class Index {

	@RequestMapping({ "/index", "/" })
	public String index(Model model) {
		model.addAttribute("login", new Login());
		return "index";
	}

}
