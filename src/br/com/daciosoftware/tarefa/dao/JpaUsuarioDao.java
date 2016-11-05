package br.com.daciosoftware.tarefa.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.daciosoftware.tarefa.Util;
import br.com.daciosoftware.tarefa.model.Usuario;

@Repository
public class JpaUsuarioDao extends GenericDao<Usuario, Integer>{

	@SuppressWarnings("unchecked")
	public List<Usuario> lista() {
		return getEntityManager().createQuery("select u from Usuario u").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Usuario existeUsuario(Usuario usuario) {
		String jpql = "select u from Usuario u where u.email = :pemail and u.senha = :psenha";
		Query query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("pemail", usuario.getEmail());
		query.setParameter("psenha", Util.criptografaSenha(usuario.getSenha()));
		List<Usuario> lista = query.getResultList();
		return (lista.size() >0)?lista.get(0):null;
	}
}
