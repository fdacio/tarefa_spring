<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>

	<sf:form action="gravaDados" methodParam="POST" cssClass="formulario"
		modelAttribute="usuario">
		<fieldset>
			<legend>Meus Dados</legend>
			<sf:hidden path="id" />
			<table>
				<tr>
					<td><sf:errors path="nome" cssStyle="color:red; display:block" />
						<sf:errors path="email" cssStyle="color:red; display:block" /> 
						<sf:errors path="senha" cssStyle="color:red; display:block" />
						<sf:errors path="categoria" cssStyle="color:red; display:block" /> 
						<span style="color: blue; display: block">${msgSucesso}</span>
					</td>
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
				<c:if test="${usuario.administrador}">
					<tr>
						<td>Administrador? <sf:checkbox path="administrador"
								value="true"  />
						</td>
					</tr>
				</c:if>
				<tr>
					<td><sf:label path="categoria">Categoria</sf:label></td>
				</tr>
				<tr>
					<td><sf:select path="categoria.id">
							<sf:option value="0" label="Informe uma categoria" />
							<sf:options items="${categorias}" itemLabel="nome" itemValue="id" />
						</sf:select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Gravar" class="ui-button" /></td>
				</tr>

			</table>
		</fieldset>
	</sf:form>
</div>
