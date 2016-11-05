package br.com.daciosoftware.tarefa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.daciosoftware.tarefa.dao.JpaCategoriaDao;
import br.com.daciosoftware.tarefa.model.Categoria;

@Controller // -> Indica que o spring irá controlar essa classe
@Transactional // -> Para todos os métodos serem controlados por transações JPA
public class CategoriaController {

	private JpaCategoriaDao dao;

	@Autowired
	public CategoriaController(JpaCategoriaDao dao) {
		this.dao = dao;
	}

	@RequestMapping("cadastraCategoria")
	public String cadastraCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		listaCategorias(model);
		return "categoria/cadastro";
	}

	@RequestMapping("alteraCategoria")
	public String alteraCategoria(Integer id, Model model) {
		model.addAttribute("categoria", dao.buscaPorId(id));
		listaCategorias(model);
		return "categoria/cadastro";
	}

	@RequestMapping("excluiCategoria")
	public String excluiCategoria(Integer id, Model model) {
		dao.remove(dao.buscaPorId(id));
		return "redirect:cadastraCategoria";
	}

	public void listaCategorias(Model model) {
		model.addAttribute("categorias", dao.lista());
	}

	@RequestMapping(value="gravaCategoria", method = RequestMethod.POST)
	public String gravaCategoria(@Valid Categoria categoria, BindingResult result, Model model) {
		if (result.hasErrors()) {
			listaCategorias(model);
			return "categoria/cadastro";
		}
		if (categoria.getId() == null) {
			dao.adiciona(categoria);
		} else {
			dao.altera(categoria);
		}
		return "redirect:cadastraCategoria";
	}

}
