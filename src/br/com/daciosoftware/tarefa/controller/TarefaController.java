package br.com.daciosoftware.tarefa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.daciosoftware.tarefa.dao.JpaTarefaDao;
import br.com.daciosoftware.tarefa.model.Tarefa;
import br.com.daciosoftware.tarefa.model.TarefaPrioridade;
import br.com.daciosoftware.tarefa.model.Usuario;

@Controller // -> Indica que o spring irá controlar essa classe
@Transactional // -> Para todos os métodos serem controlados por transações JPA
public class TarefaController {

	
	private JpaTarefaDao dao;
	private LoginController login;

	@Autowired
	public TarefaController(JpaTarefaDao dao, LoginController login) {
		this.dao = dao;
		this.login = login;
	}
	
	
	public void setJpaTarefaDao(JpaTarefaDao dao){
		this.dao = dao;
	}

	@RequestMapping("novaTarefa")
	public String novaTarefa(Model model) {
		Tarefa tarefa = new Tarefa();
		tarefa.setDataTarefa(Calendar.getInstance());
		tarefa.setUsuario(login.getUsuarioLogado());

		model.addAttribute("tarefa", tarefa);
		model.addAttribute("prioridades", TarefaPrioridade.values());

		return "tarefa/nova";
	}

	@RequestMapping(value = "/gravaTarefa", method = RequestMethod.POST)
	public String gravaTarefa(@Valid Tarefa tarefa, BindingResult result, Model model) {
		if (tarefa.getId() == null) {
			if (result.hasErrors()) {
				model.addAttribute("prioridades", TarefaPrioridade.values());
				return "tarefa/nova";
			}
			dao.adiciona(tarefa);
		} else {
			if (result.hasErrors()) {
				return "tarefa/altera";
			}

			if (!tarefa.isFinalizada()) {
				tarefa.setDataFinalizacao(null);
			}

			dao.altera(tarefa);
		}
		return "redirect:minhasTarefas";
	}

	@RequestMapping("excluiTarefa")
	public String excluiTarefa(Integer id, Model model) {
		dao.remove(dao.buscaPorId(id));
		return "redirect:minhasTarefas";
	}

	@RequestMapping("alteraTarefa")
	public String alteraTarefa(Integer id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/altera";
	}

	@RequestMapping("todasTarefas")
	public String todasTarefas(HttpSession session, Model model) {
		List<Tarefa> tarefas = dao.lista();
		model.addAttribute("tarefas", tarefas);
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado.isAdministrador()) {
			return "tarefa/listaTodas";
		} else {
			model.addAttribute("mensagem", "Usuário não autorizado");
			return "goLogout";
		}
	}

	@RequestMapping("minhasTarefas")
	public String minhasTarefas(Model model) {
		List<Tarefa> tarefas = dao.lista(login.getUsuarioLogado());
		model.addAttribute("tarefas", tarefas);
		return "tarefa/lista";
	}

	@RequestMapping("consultarMinhasTarefas")
	public String consultarMinhasTarefas(Tarefa tarefa, Model model) {
		List<Tarefa> tarefas = dao.lista(tarefa, login.getUsuarioLogado());
		model.addAttribute("tarefas", tarefas);
		return "tarefa/lista";
	}

	@RequestMapping("consultarTodasTarefas")
	public String consultarTodasTarefas(Tarefa tarefa, Model model) {
		List<Tarefa> tarefas = dao.lista(tarefa);
		model.addAttribute("tarefas", tarefas);
		return "tarefa/listaTodas";
	}

	@RequestMapping("finalizaTarefa")
	public void finalizaTarefa(Integer id, HttpServletResponse response) throws IOException {
		Tarefa tarefa = dao.buscaPorId(id);
		if (tarefa.getUsuario().getId() ==login.getUsuarioLogado().getId()) {
			tarefa.setFinalizada(true);
			tarefa.setDataFinalizacao(Calendar.getInstance());
			dao.altera(tarefa);
			String resposta = new SimpleDateFormat("dd/MM/yyyy").format(tarefa.getDataFinalizacao().getTime());
			response.setStatus(200);
			response.getWriter().write(resposta);
		}
	}

}
