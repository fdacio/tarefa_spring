package br.com.daciosoftware.tarefa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("index")|| uri.endsWith("/") || uri.contains("resources")) {
			return true;
		}

		if (uri.endsWith("goLogin") || uri.endsWith("goLogout") ) {
			return true;
		}
		
		if (uri.endsWith("cadastrese")||uri.endsWith("cadastraUsuario")){
			return true;
		}
		
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		
		response.sendRedirect("index");
		
		return false;
	}
}