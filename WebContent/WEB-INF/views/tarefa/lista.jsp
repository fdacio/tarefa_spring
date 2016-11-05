<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>

	<form action="consultarMinhasTarefas" method="post" class="formulario">
		<fieldset>
			<legend>Minhas Tarefas</legend>
			<table class="consulta" >
				<tr>
					<td><label>Descrição</label></td>
				</tr>
				<tr>
				<tr>
					<td><input type="text" name="descricao" /><input type="submit"
				value="Pesquisar" /></td>
				</tr>
				<tr>
			</table>
			
			<table class="lista">
				<tr>
				<th>Data</th>
				<th>Prioridade</th>
				<th>Descrição</th>
				<th class="finaliza">Situação</th>
				<th class="acoes">Ações</th>
				</tr>
				<c:forEach var="tarefa" items="${tarefas}" varStatus="id">
					<tr>

						<td><fmt:formatDate value="${tarefa.dataTarefa.time}"
								pattern="dd/MM/yyy" /></td>
						<td>${tarefa.prioridade.nome}</td>		
						<td>${tarefa.descricao}</td>

						<td id="tarefa_${tarefa.id}" class="finalizar"><c:choose>
								<c:when test="${tarefa.finalizada}">
									<fmt:formatDate value="${tarefa.dataFinalizacao.time}"
										pattern="dd/MM/yyy" var="dataFF" />
							Finalizada em ${dataFF}
							 
						</c:when>
								<c:otherwise>
									<a href="#" onclick="finalizaTarefa(${tarefa.id})"
										class="finaliza"> Finalizar </a>
								</c:otherwise>
							</c:choose></td>

						<td class="acoes"><a href="alteraTarefa?id=${tarefa.id}">Alterar</a>
							<a href="excluiTarefa?id=${tarefa.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</form>
</div>