<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<sf:form action="gravaCategoria" methodParam="POST"
		modelAttribute="categoria" cssClass="formulario">
		<fieldset>
			<legend>Cadastro de Categoria</legend>
			<table>
				<tr>
					<td><sf:errors path="nome" cssStyle="color: red" /></td>
				</tr>
				<tr>
					<td><sf:label path="nome">Nome</sf:label></td>
				</tr>

				<tr>
					<td><sf:hidden path="id" />
						<sf:input path="nome" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Gravar" /></td>
				</tr>

			</table>
		</fieldset>

	</sf:form>

	<sf:form action="#" method="post" cssClass="formulario">
		<fieldset>
			<legend>Categorias</legend>

			<table class="lista">
				<tr>
					<th>Id</th>
					<th>Categoria</th>
					<th class="acoes">Ações</th>
				</tr>
				<c:forEach var="categoriaLista" items="${categorias}">
					<tr>
						<td>${categoriaLista.id}</td>
						<td>${categoriaLista.nome}</td>
						<td><a href="alteraCategoria?id=${categoriaLista.id}">Alterar</a>
							<a href="excluiCategoria?id=${categoriaLista.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</sf:form>
</div>