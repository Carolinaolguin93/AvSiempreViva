package controller.type;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TypeService;

@WebServlet("/types/edit.do")
public class EditTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private TypeService typeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.typeService = new TypeService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = typeService.findByName("type");
		req.setAttribute("type", type);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/types/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("type");

		String type = typeService.update("name");

		if (type == "") {
			req.setAttribute("type", type);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/types/edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("/turismo/types/index.do");
		}
	}
}