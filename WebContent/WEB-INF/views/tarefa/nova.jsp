<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form action="gravaTarefa" methodParam="POST" cssClass="formulario" modelAttribute="tarefa">
		<fieldset>
			<legend>Nova Tarefa</legend>
			<sf:hidden path="dataTarefa" />
			<sf:hidden path="usuario.id" />
			<table>
				<tr>
					<td>
						<sf:errors path="prioridade" cssStyle="color:red; display: block" />
						<sf:errors path="descricao" cssStyle="color:red; display: block" />
					</td>
				</tr>
				
				<tr>
					<td><label for="prioridade">Prioridade</label></td>
				</tr>
				<tr>
					<td>
					
						<sf:select path="prioridade">
							<sf:option value ="" label="Informe uma prioridade"/>
							<sf:options items="${prioridades}" itemLabel="nome"/>
						</sf:select> 
				
					</td>
				</tr>

				<tr>
					<td><label for="descricao">Descrição</label></td>
				</tr>
				<tr>
					<td><sf:textarea path="descricao" cols="50" rows="5"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Gravar" class="ui-button" /><input
						type="button" value="Cancelar"
						onclick="window.location.href='minhasTarefas'" class="ui-button" /></td>
				</tr>

			</table>
		</fieldset>
	</sf:form>
</div>
