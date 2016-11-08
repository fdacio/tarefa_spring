<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>

	<sf:form action="consultarTarefa" methodParm="POST"
		cssClass="formulario" modelAttribute="tarefa">
		<fieldset>
			<legend>${titulo}</legend>
			<table class="consulta">
				<tr>
					<td><sf:label path="descricao">Descrição</sf:label> <sf:hidden
							path="usuario.id" /></td>
				</tr>
				<tr>
					<td><sf:input path="descricao" /> <input type="submit"
						value="Pesquisar" /></td>
				</tr>

			</table>
		</fieldset>
	</sf:form>

	<table class="lista">
		<tr>
			<th class="pessoa">Pessoa</th>
			<th>Data</th>
			<th>Prioridade</th>
			<th>Descrição</th>
			<th class="finaliza">Situação</th>
			<th class="acoes">Ações</th>
		</tr>
		<c:forEach var="tarefa" items="${tarefas}">
			<tr>
				<td>${tarefa.usuario.nome}</td>
				<td><fmt:formatDate value="${tarefa.dataTarefa.time}"
						pattern="dd/MM/yyy" /></td>
				<td>${tarefa.prioridade.nome}</td>
				<td>${tarefa.descricao}</td>

				<td id="tarefa_${tarefa.id}" class="center"><c:choose>
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
</div>