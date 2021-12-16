package controller.type;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TypeService;

@WebServlet("/types/index.do")
public class ListTypeServlet extends HttpServlet implements Servlet {

	
	private static final long serialVersionUID = -7489193587936413929L;
	private TypeService typeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.typeService = new TypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> types = typeService.list();
		req.setAttribute("types", types);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/types/index.jsp");
		dispatcher.forward(req, resp);

	}

}
