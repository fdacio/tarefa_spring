package br.com.daciosoftware.tarefa.model;

public enum TarefaPrioridade {
	BAIXA("Baixa"), 
	MEDIA("Media"), 
	ALTA("Alta"),
	URGENTE("Urgente");
	
	private String nome;

	TarefaPrioridade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
