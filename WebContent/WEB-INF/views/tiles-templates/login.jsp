<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>

	<sf:form action="goLogin" methodParam="POST" modelAttribute="login"
		cssClass="login" >
		<table>
			<tr>
				<td colspan="3">
					<div id="errorLogin">
						<sf:errors path="email" />
						<sf:errors path="senha" />
						<span>${mensagemLogin}</span>
					</div>
				</td>
			</tr>

			<tr>
				<td><sf:label path="email">Email</sf:label></td>
				<td><sf:label path="senha">Senha</sf:label></td>
				<td></td>
			</tr>
			<tr>
				<td><sf:input path="email" /></td>
				<td><sf:password path="senha" /></td>
				<td><input type="submit" value="Entrar" /> <input
					type="button" value="Cadastre-se"
					onclick="window.location.href='cadastrese'" /></td>
			</tr>
		</table>

	</sf:form>
</div>
