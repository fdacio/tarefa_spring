package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.model.Tarefa;
import br.com.daciosoftware.tarefa.model.Usuario;

@Repository
public class JpaTarefaDao extends GenericDao<Tarefa, Integer>{

	@SuppressWarnings("unchecked")
	public List<Tarefa> lista() {
		return getEntityManager().createQuery("select t from Tarefa t").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> lista(Usuario usuario) {
		return getEntityManager().createQuery("select t from Tarefa t where t.usuario.id = "+usuario.getId()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> lista(Tarefa tarefa, Usuario usuario) {
		return getEntityManager().createQuery("select t from Tarefa t where t.usuario.id = "+usuario.getId()+" and t.descricao like "+"'%"+tarefa.getDescricao()+"%'").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> lista(Tarefa tarefa) {
		return getEntityManager().createQuery("select t from Tarefa t where t.descricao like "+"'%"+tarefa.getDescricao()+"%'").getResultList();
	}
	
	
}
