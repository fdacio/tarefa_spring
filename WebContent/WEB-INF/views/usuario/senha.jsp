<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>


	<sf:form action="gravaSenha" methodParam="POST" class="formulario" modelAttribute="alteraSenha">
		<fieldset>
			<legend>Alterar Senha</legend>
			<table>
				<tr>
					<td>
						<sf:errors path="novaSenha" cssStyle="color:red; display:block"></sf:errors>
						<sf:errors path="confirmaNovaSenha" cssStyle="color:red; display:block"></sf:errors>
						<span style="color: red">${mensagemSenha}</span>
						<span style="color: blue">${mensagemSenhaSucesso}</span>
					</td>
				</tr>
				<tr>
					<td><sf:label path="novaSenha">Nova Senha</sf:label></td>
				</tr>
				<tr>
					<td><sf:password path="novaSenha" /></td>
				</tr>

				<tr>
					<td><sf:label path="confirmaNovaSenha">Confirmar Nova Senha</sf:label></td>
				</tr>
				<tr>
					<td><sf:password path="confirmaNovaSenha" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Alterar Senha"
						class="ui-button" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>
</div>
