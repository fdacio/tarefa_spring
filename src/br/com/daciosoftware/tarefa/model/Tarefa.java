package br.com.daciosoftware.tarefa.model;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.daciosoftware.tarefa.dao.Entidade;

@Entity
@NamedQueries({
@NamedQuery(name=Tarefa.LISTA_TODAS, query = "select t from Tarefa t"),
@NamedQuery(name=Tarefa.TAREFAS_POR_USUARIO, query = "select t from Tarefa t where t.usuario.id=:pusuarioId"),
@NamedQuery(name=Tarefa.TAREFAS_POR_DESCRICAO, query = "select t from Tarefa t where t.descricao like ':pdescricao'"),
@NamedQuery(name=Tarefa.TAREFAS_POR_USUARIO_DESCRICAO, query = "select t from Tarefa t where t.descricao like ':pdescricao' and t.usuario.id = :pusuarioId")
})
public class Tarefa implements Entidade{
	
	public static final String LISTA_TODAS = "Tarefa.ListaTodas";
	public static final String TAREFAS_POR_USUARIO = "Tarefa.TarefasPorUsuario";
	public static final String TAREFAS_POR_DESCRICAO = "Tarefa.TarefasPorDescricao";
	public static final String TAREFAS_POR_USUARIO_DESCRICAO = "Tarefa.TarefasPorUsuarioDescricao";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 5, message = "A descrição tem que ter no mínimo 5 carateres")
	@Basic(optional = false)
	private String descricao;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)// Para o JPA
	@DateTimeFormat(pattern="dd/MM/yyyy") //Para o Spring
	@NotNull(message = "Data da tarefa é obrigatória")
	private Calendar dataTarefa;
	
	private boolean finalizada;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataFinalizacao;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@NotNull(message = "A prioridade é obrigatória")
	@Enumerated(EnumType.STRING)
	private TarefaPrioridade prioridade;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Calendar getDataTarefa() {
		return dataTarefa;
	}

	public void setDataTarefa(Calendar dataTarefa) {
		this.dataTarefa = dataTarefa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TarefaPrioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(TarefaPrioridade prioridade) {
		this.prioridade = prioridade;
	}

	
}
