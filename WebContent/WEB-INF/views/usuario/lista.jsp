<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<form action="#" method="post" class="formulario">
		<fieldset>
			<legend>Lista de Usuários</legend>

			<table class="consulta">
				<tr>
					<td><label>Nome</label></td>
				</tr>
				<tr>
					<td><input type="text" name="nome" /><input type="submit"
						value="Pesquisar" /></td>
				</tr>

			</table>

			<table class="lista">
				<tr>
					<th>Nome</th>
					<th>Email</th>
					<th>Categoria</th>
					<th>Bloqueado</th>
					<th>Ações</th>
				</tr>
				<c:forEach var="usuario" items="${usuarios}">
					<tr>
						<td>${usuario.nome}</td>
						<td>${usuario.email}</td>
						<td>${usuario.categoria.nome}</td>
						<td class="center">${(usuario.bloqueado)?'SIM':'NÃO'}</td>
						<td class="center">
							<a href="bloquearUsuario?id=${usuario.id}">${(usuario.bloqueado)?'Desbloquear':'Bloquear'}</a>
							<a href="usuarioTarefas?id=${usuario.id}">Tarefas</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</form>
</div>