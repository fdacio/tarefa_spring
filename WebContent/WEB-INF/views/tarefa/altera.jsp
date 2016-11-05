<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<div>
	<sf:form action="gravaTarefa" methodParam="post" cssClass="formulario"
		modelAttribute="tarefa">
		<fieldset>
			<legend>Aterar Tarefa</legend>
			<sf:hidden path="id" />
			<sf:hidden path="usuario.id" />
			<table>
				<tr>
					<td><sf:errors path="dataTarefa" cssStyle="color:red; display: block" />
					<sf:errors path="prioridade" cssStyle="color:red; display: block" />
					<sf:errors path="descricao" cssStyle="color:red; display: block" /></td>
				</tr>
				<tr>
					<td><sf:label path="dataTarefa">Data</sf:label>
				</tr>
				<tr>
					<td>
						 <sf:input path="dataTarefa" class="date-picker"/>	
					</td>
				</tr>
				<tr>
					<td><label for="prioridade">Prioridade</label></td>
				</tr>
				<tr>
					<td><sf:select path="prioridade">
							<sf:option value="" label="Informe uma prioridade" />
							<sf:options items="${prioridades}" itemLabel="nome" />
						</sf:select></td>
				</tr>

				<tr>
					<td><label for="descricao">Descrição</label></td>
				</tr>
				<tr>
					<td><sf:textarea path="descricao" rows="5"	cols="50" /></td>
				</tr>

				<tr>
					<td>Finalizado? <sf:checkbox path="finalizada" value="true" />
					</td>
				</tr>
				<tr>
					<td><sf:label path="dataFinalizacao">Data Finalização</sf:label></td>
				</tr>
				<tr>
					<td><sf:input path="dataFinalizacao" class="date-picker"/></td>
				</tr>


				<tr>
					<td><input type="submit" value="Gravar" class="ui-button" />
						<input type="button" value="Cancelar"
						onclick="window.location.href='minhasTarefas'" class="ui-button" /></td>
				</tr>

			</table>
		</fieldset>
	</sf:form>
</div>