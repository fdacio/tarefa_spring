package br.com.daciosoftware.tarefa.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AlteraSenha {
	
	@NotEmpty(message = "Nova senha é obrigatório")
	@Size(min = 6, message = "Nova senha no mínimo com 6 caracteres")
	private String novaSenha;
	private String confirmaNovaSenha;
	
	public AlteraSenha(){
		
	}
	
	public AlteraSenha(String novaSenha, String confirmaNovaSenha){
		this.novaSenha = novaSenha;
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

}
