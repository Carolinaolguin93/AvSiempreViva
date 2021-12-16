package controller.promotion;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.Promotion;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/edit.do")
public class EditPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromotionService promotionService;
	private AttractionService attractionService;


	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
		this.attractionService = new AttractionService();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promotion promotion = promotionService.find(id);
		req.setAttribute("promotion", promotion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String[] attractionString = req.getParameterValues("attractions");
		Attraction[] arrayAttractions = new Attraction[attractionString.length];
		for (int i = 0; i < attractionString.length; i++) {
			for (Attraction attraction : attractionService.list()) {
				if (attraction.getId() == Integer.valueOf(attractionString[i])) {
					arrayAttractions[i] = attraction;
				}
			}
		}
		String typePromo = req.getParameter("typePromo");

		Promotion promo = promotionService.update(id, name, type,arrayAttractions, typePromo);
		if (promo.isValid()) {
			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			List<Attraction> attractions = attractionService.list();
			req.setAttribute("attractions", attractions);
			req.setAttribute("promotion", promo);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/edit.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
