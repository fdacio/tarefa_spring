package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.Util;
import br.com.daciosoftware.tarefa.model.Login;
import br.com.daciosoftware.tarefa.model.Usuario;

@Repository
public class JpaUsuarioDao extends GenericDao<Usuario, Integer>{

	public List<Usuario> lista() {
		return (List<Usuario>) getEntityManager().createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
	

	@SuppressWarnings("unchecked")
	public Usuario getUsuarioLogin(Login login) {
		String jpql = "select u from Usuario u where u.email = :pemail and u.senha = :psenha";
		Query query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("pemail", login.getEmail());
		query.setParameter("psenha", Util.criptografaSenha(login.getSenha()));
		List<Usuario> lista = query.getResultList();
		return (lista.size() >0)?lista.get(0):null;
		//return (Usuario) query.getSingleResult();
	}
}
