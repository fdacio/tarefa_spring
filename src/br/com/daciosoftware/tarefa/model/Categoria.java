package br.com.daciosoftware.tarefa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.daciosoftware.tarefa.dao.Entidade;

@Entity
public class Categoria implements Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Nome da categoria é obrigatório")
	@Basic(optional = false)
	@Column(length = 20)
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		return this.id + "-" + this.nome;
	}
	
}
