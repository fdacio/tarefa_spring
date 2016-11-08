package br.com.daciosoftware.tarefa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.daciosoftware.tarefa.dao.Entidade;

@Entity
public class Usuario implements Entidade{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@Email(message = "Email inválido")
	@NotEmpty(message = "Email é obrigatório")
	private String email;
	
	@Size(min = 6, message = "Senha com no mínimo 6 caracteres")
	private String senha;
	
	@Transient
	private String confirmaSenha;
	
	private boolean administrador;
	
	@OneToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	private boolean bloqueado;
	
	@OneToMany
	private List<Tarefa> tarefas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString(){
		return this.nome + "-" +this.email;
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
}
