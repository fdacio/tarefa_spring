<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form action="cadastraUsuario" methodParam="POST" cssClass="formulario"
		modelAttribute="usuario">
		<fieldset>
			<legend>Cadastre-se</legend>
			<table>
				<tr>
					<td><sf:errors path="nome" cssStyle="color:red; display:block" />
						<sf:errors path="email" cssStyle="color:red; display:block" /> 
						<sf:errors path="senha" cssStyle="color:red; display:block" /> 
						<span style="color: red; display: block">${confirmaSenha}</span> 
						<span style="color: blue; display: block">${msgSucesso}</span></td>
				</tr>

				<tr>
					<td><sf:label path="nome">Nome</sf:label></td>
				</tr>
				<tr>
					<td><sf:input path="nome" /></td>
				</tr>
				<tr>
					<td><sf:label path="email">Email</sf:label></td>
				</tr>
				<tr>
					<td><sf:input path="email" /></td>
				</tr>
				<tr>
					<td><sf:label path="senha">Senha</sf:label></td>
				</tr>
				<tr>
					<td><sf:password path="senha" /></td>
				</tr>

				<tr>
					<td><sf:label path="confirmaSenha">Confirma Senha</sf:label></td>
				</tr>
				<tr>
					<td><sf:password path="confirmaSenha" /></td>
				</tr>
				<tr>
					<td><sf:label path="categoria">Categoria</sf:label></td>
				</tr>
				<tr>
					<td><sf:select path="categoria.id">
							<sf:option value="0" label="Informe uma categoria" />
							<sf:options items="${categorias}" itemLabel="nome" itemValue="id" />
						</sf:select></td>
				</tr>

				<c:if test="${primeiro}">
					<tr>
						<td><input type="hidden" value="true" name="administrador" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td><input type="submit" value="Cadastrar" class="ui-button" /></td>
				</tr>

			</table>
		</fieldset>
	</sf:form>
</div>
