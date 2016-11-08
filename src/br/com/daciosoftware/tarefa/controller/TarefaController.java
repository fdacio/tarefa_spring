package br.com.daciosoftware.tarefa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

	
	private String listaTarefa(Tarefa tarefa, Usuario usuario, Model model){
		List<Tarefa> tarefas = dao.listaTarefa(tarefa, usuario);
		model.addAttribute("tarefa", tarefa);
		model.addAttribute("tarefas", tarefas);
		System.out.println("Lista Tarefa usuario: " + usuario);
		if(usuario != null){
			model.addAttribute("titulo", "Minhas Tarefas");
		}else{
			model.addAttribute("titulo", "Todas Tarefas");
		}
		return "tarefa/lista";
	}

	
	@RequestMapping("minhasTarefas")
	public String minhasTarefas(Model model) {
		Tarefa tarefa = new Tarefa();
		tarefa.setUsuario(login.getUsuarioLogado());
		return listaTarefa(tarefa, tarefa.getUsuario(), model);
		
	}

	@RequestMapping("todasTarefas")
	public String todasTarefas(Model model) {
		if (login.getUsuarioLogado().isAdministrador()) {
			Tarefa tarefa = new Tarefa();
			tarefa.setUsuario(null);
			return listaTarefa(tarefa, tarefa.getUsuario(), model);
		} else {
			return "goLogout";
		}
	}

	@RequestMapping("consultarTarefa")
	public String consultarTarefa(Tarefa tarefa,  Model model) {
		return listaTarefa(tarefa, tarefa.getUsuario(), model);
	}

	@RequestMapping("usuarioTarefas")
	public String usuarioTarefas(Integer idUsuario, Model model) {
		Tarefa tarefa = new Tarefa();
		tarefa.setUsuario(dao.getEntityManager().getReference(Usuario.class, idUsuario));
		return listaTarefa(tarefa, tarefa.getUsuario(), model);
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
