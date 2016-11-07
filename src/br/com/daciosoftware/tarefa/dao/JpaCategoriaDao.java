package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.model.Categoria;

@Repository
public class JpaCategoriaDao extends GenericDao<Categoria, Integer>{

	public List<Categoria> lista() {
		return getEntityManager().createQuery("select u from Categoria u", Categoria.class).getResultList();
	}

}
