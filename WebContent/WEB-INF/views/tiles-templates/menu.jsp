<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="div_menu">
	<ul id="menu">
		<li class="ui-widget-header"><div>Tarefas</div></li>
		<li><div>
				<a href="novaTarefa"><span>Nova Tarefa</span></a>
			</div></li>
		<li><div>
				<a href="minhasTarefas"><span>Minhas Tarefas</span></a>
			</div></li>
		<li><div>
				<a href="#"><span>Relatório</span></a>
			</div></li>
		<c:if test="${usuarioLogado.administrador }">
			<li class="ui-widget-header"><div>Administrador</div></li>
			<li><div>
					<a href="todasTarefas"><span>Todas as Tarefas</span></a>
				</div></li>
			<li><div>
					<a href="todosUsuarios"><span>Todos os Usuários</span></a>
				</div></li>
			<li><div>
					<a href="cadastraCategoria"><span>Categorias</span></a>
				</div></li>

		</c:if>
		<li class="ui-widget-header"><div class="user_name">${usuarioLogado.nome}</div></li>
		<li><div>
				<a href="alteraSenha"><span>Alterar Senha</span></a>
			</div></li>
		<li><div>
				<a href="dadosUsuario"><span>Meus Dados</span></a>
			</div></li>
		<li><div>
				<a href="logout"><span>Sair</span></a>
			</div></li>
	</ul>
</div>