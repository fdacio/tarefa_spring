<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<form action="consultarTarefa" method="post" class="formulario">
		<fieldset>
			<legend>Todas Tarefas</legend>
			<table class="consulta">
				<tr>
					<td><label>Descrição</label></td>
				</tr>
				
				<tr>
					<td><input type="text" name="descricao" /><input
						type="submit" value="Pesquisar" /></td>
				</tr>
				
			</table>


			<table class="lista">
				<tr>
					<th class="pessoa">Pessoa</th>
					<th>Data</th>
					<th>Descrição</th>
					<th class="finaliza">Situação</th>
					<th class="acoes">Ações</th>
				</tr>
				<c:forEach var="tarefa" items="${tarefas}" varStatus="id">
					<tr>
						<td>${tarefa.usuario.nome}</td>
						<td><fmt:formatDate value="${tarefa.dataTarefa.time}"
								pattern="dd/MM/yyy" /></td>
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

						<td class="center"><a href="alteraTarefa?id=${tarefa.id}">Alterar</a>
							<a href="excluiTarefa?id=${tarefa.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</form>
</div>