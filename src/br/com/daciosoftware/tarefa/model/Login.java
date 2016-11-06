package br.com.daciosoftware.tarefa.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Login {

	@NotEmpty(message = "Informe o Email")
	private String email;
	@NotEmpty(message = "Informe a Senha")
	private String senha;
	
	public Login(){}
	
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

	@Override
	public String toString() {
		return "Login [email=" + email + ", senha=" + senha + ", getEmail()=" + getEmail() + ", getSenha()="
				+ getSenha() + "]";
	}
	
	
	
	
}
