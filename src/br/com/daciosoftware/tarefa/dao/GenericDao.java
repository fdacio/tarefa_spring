package br.com.daciosoftware.tarefa.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao <E extends Entidade, I>{

	@PersistenceContext
	private EntityManager manager;
	
	public GenericDao() {
    }
	
	public EntityManager getEntityManager(){
		return manager;
	}
	
	public void adiciona(E entidade) {
		manager.persist(entidade);
	}

	public void altera(E entidade) {
		manager.merge(entidade);
	}

	public void remove(E entidade) {
		E entidadeARemover = manager.merge(entidade);
		manager.remove(entidadeARemover);
	}
	
	public E buscaPorId(I id) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<E> entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
		return manager.find(entityClass, id);
	}

	
	public abstract List<E> lista();

}
