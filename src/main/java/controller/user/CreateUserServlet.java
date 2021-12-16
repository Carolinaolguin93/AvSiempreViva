package controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;

@WebServlet("/users/create.do")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Double coins = Double.parseDouble(req.getParameter("coins"));
		Double time = Double.parseDouble(req.getParameter("time"));
		Boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
		String type = req.getParameter("type");

		User user = userService.create(username, password, coins, time, admin, type);
		
		if (user.isValid()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("user",user);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}