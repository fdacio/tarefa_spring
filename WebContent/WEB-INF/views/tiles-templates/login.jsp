<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div id="login">
	
	<form action="login" method="post">
		<table>
			<tr>
				<td><label for="email">Email</label></td>
				<td><label for="senha">Senha</label></td>
				<td><span style="color: red">${mensagemLogin}</span></td>
			</tr>
			<tr>
				<td><input type="text" name="email" value="${usuario.email }"/></td>
				<td><input type="password" name="senha" value="${usuario.senha }"/></td>
				<td><input type="submit" value="Entrar" /> <input
					type="button" value="Cadastre-se"
					onclick="window.location.href='cadastrese'" /></td>
			</tr>
		</table>

	</form>
</div>
