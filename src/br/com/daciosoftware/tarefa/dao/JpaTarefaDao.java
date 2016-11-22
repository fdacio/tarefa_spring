package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.model.Tarefa;
import br.com.daciosoftware.tarefa.model.Usuario;

@Repository
public class JpaTarefaDao extends GenericDao<Tarefa, Integer> {

	public List<Tarefa> lista() {
		return getEntityManager().createNamedQuery(Tarefa.LISTA_TODAS, Tarefa.class).getResultList();
	}

	public List<Tarefa> listaTarefa(Tarefa tarefa, Usuario usuario, int pag) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Tarefa> query = builder.createQuery(Tarefa.class);
		Root<Tarefa> from = query.from(Tarefa.class);

		Predicate predicate = builder.and(); //1=1

		if (tarefa != null) {
			if (tarefa.getDescricao() != null && !tarefa.getDescricao().equals("")) {
				predicate = builder.and(predicate,
						builder.like(from.<String>get("descricao"), "%" + tarefa.getDescricao() + "%"));
			}
		}

		if (usuario != null) {
			if (usuario.getId() != null && usuario.getId() > 0) {
				predicate = builder.and(predicate, builder.equal(from.join("usuario").get("id"), usuario.getId()));
			}
		}

		TypedQuery<Tarefa> typedQuery = getEntityManager()
				.createQuery(query.select(from).where(predicate).orderBy(builder.desc(from.get("dataTarefa"))));

		typedQuery.setMaxResults(5);
		typedQuery.setFirstResult(pag);
		return typedQuery.getResultList();
	}

}
