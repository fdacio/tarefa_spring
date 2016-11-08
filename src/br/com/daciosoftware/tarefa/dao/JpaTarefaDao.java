package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.model.Tarefa;
import br.com.daciosoftware.tarefa.model.Usuario;

@Repository
public class JpaTarefaDao extends GenericDao<Tarefa, Integer>{
	
	public List<Tarefa> lista() {
		return getEntityManager().createNamedQuery(Tarefa.LISTA_TODAS, Tarefa.class).getResultList();
	}

	public List<Tarefa> lista(Usuario usuario) {
		TypedQuery<Tarefa> query = getEntityManager().createNamedQuery(Tarefa.TAREFAS_POR_USUARIO, Tarefa.class);
		query.setParameter("pusuarioId", usuario.getId());
		return query.getResultList();
	}

	public List<Tarefa> lista(Tarefa tarefa) {
		TypedQuery<Tarefa> query = getEntityManager().createNamedQuery(Tarefa.TAREFAS_POR_DESCRICAO, Tarefa.class);
		query.setParameter("pdescricao", "%"+tarefa.getDescricao()+"%");
		return query.getResultList();
	}

	public List<Tarefa> lista(Tarefa tarefa, Usuario usuario) {
		TypedQuery<Tarefa> query = getEntityManager().createNamedQuery(Tarefa.TAREFAS_POR_USUARIO_DESCRICAO, Tarefa.class);
		query.setParameter("pdescricao", "%"+tarefa.getDescricao()+"%");
		query.setParameter("pusuarioId", usuario.getId());
		return query.getResultList();
	}
	
	
}
