package controller.promotion;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/promotions/create.do")
public class CreatePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 8874396921558135332L;
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
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		if(req.getParameterValues("attractions") == null) {
			List<Attraction> attractions = attractionService.list();
			req.setAttribute("attractions", attractions);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promotions/create.jsp");
			dispatcher.forward(req, resp);
		}
			
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

	
		Promotion promo = promotionService.create(name, type, typePromo, arrayAttractions);

		if(!(type.equals("Visita_Guiada") && attractionString.length != 3)) {
			resp.sendRedirect("/turismo/promotions/index.do");
		} else {
			List<Attraction> attractions = attractionService.list();
			req.setAttribute("attractions", attractions);
			req.setAttribute("promotion", promo);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promotions/create.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
