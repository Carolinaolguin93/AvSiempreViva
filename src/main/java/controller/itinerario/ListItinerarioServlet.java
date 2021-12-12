package controller.itinerario;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sugerencia;
import model.User;
import services.ItinerarioService;

@WebServlet("/itinerario.do")
public class ListItinerarioServlet extends HttpServlet {

	private static final long serialVersionUID = -4134285020573067353L;
	private ItinerarioService itinerarioService;

	@Override
	public void init() throws ServletException {
		this.itinerarioService = new ItinerarioService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");

		ArrayList<Sugerencia> itinerario = itinerarioService.find(user.getId());
		req.setAttribute("itinerario", itinerario);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/itinerario.jsp");
		dispatcher.forward(req, resp);

	}
	
}
