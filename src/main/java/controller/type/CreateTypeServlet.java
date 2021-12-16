package controller.type;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TypeService;

@WebServlet("/types/create.do")
public class CreateTypeServlet extends HttpServlet {


	private static final long serialVersionUID = 2741571492726489107L;
	private TypeService typeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.typeService = new TypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/types/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("type");

		String type = typeService.create(name);
		
		if (type == "") {
			req.setAttribute("type", type);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("/turismo/types/index.do");
			
		}

	}

}