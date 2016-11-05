<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<h3>Todos Usu�rios</h3>
	<table class="lista">
		<tr>
			<th>Nome</th>
			<th>Email</th>
			<th>A��es</th>
		</tr>
		<c:forEach var="usuario" items="${usuarios}" varStatus="id">
			<tr>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
				<td class="acoes"><a href="alteraUsuario?id=${usuario.id}">Alterar</a> <a
					href="excluiUsuario?id=${usuario.id}">Excluir</a></td>
			</tr>
		</c:forEach>
	</table>
</div>