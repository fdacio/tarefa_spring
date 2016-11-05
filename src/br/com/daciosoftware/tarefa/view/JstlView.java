package br.com.daciosoftware.tarefa.view;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;


import br.com.daciosoftware.tarefa.model.Usuario;


public class JstlView extends InternalResourceView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Determine the path for the request dispatcher.
		String dispatcherPath = prepareForRendering(request, response);

		// set original view being asked for as a request parameter
		request.setAttribute("partial", dispatcherPath.replace("/WEB-INF/views/", ""));
		//request.setAttribute("partial", dispatcherPath.substring(dispatcherPath.lastIndexOf("/") + 1));
	
		request.setAttribute("model", model);
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		request.setAttribute("usuario", usuario);		
				
		// force everything to be template.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jstl-tamplates/template.jsp");
		requestDispatcher.forward(request, response);

	}

}
