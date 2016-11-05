<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div>


	<form action="gravaSenha" method="post" class="formulario">
		<fieldset>
			<legend>Alterar Senha</legend>
			<table>
				<tr>
					<td><span style="color: red">${mensagemSenha}</span></td>
				</tr>
				<tr>
					<td><label for="nova_senha">Nova Senha</label></td>
				</tr>
				<tr>
					<td><input type="password" name="nova_senha" id="nova_senha" /></td>
				</tr>

				<tr>
					<td><label for="confirma_senha">Confirmar Nova Senha</label></td>
				</tr>
				<tr>
					<td><input type="password" name="confirma_senha"
						id="confirma_senha" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Alterar Senha"
						class="ui-button" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
