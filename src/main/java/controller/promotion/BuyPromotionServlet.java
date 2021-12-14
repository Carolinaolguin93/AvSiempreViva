package controller.promotion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sugerencia;
import model.User;
import persistence.commons.DAOFactory;
import services.BuyPromotionService;
import services.ItinerarioService;

@WebServlet("/promotions/buy.do")
public class BuyPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = -1986947871936493104L;
	private BuyPromotionService buyPromotionService;
	private ItinerarioService itinerarioService;


	@Override
	public void init() throws ServletException {
		super.init();
		this.buyPromotionService = new BuyPromotionService();
		this.itinerarioService = new ItinerarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer promotionId = Integer.parseInt(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		Map<String, String> errors = buyPromotionService.buy(user.getId(), promotionId);
		
		ArrayList<Sugerencia> itinerario = itinerarioService.find(user.getId());
		req.setAttribute("itinerario", itinerario);
		
		User user2 = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/promotions/index.do");
		dispatcher.forward(req, resp);
	}
}
