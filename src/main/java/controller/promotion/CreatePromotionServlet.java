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
import model.Sugerencia;
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
		Integer attraction1 = Integer.parseInt(req.getParameter("attraction1"));
		Integer attraction2 = Integer.parseInt(req.getParameter("attraction2"));
		Integer attraction3 = Integer.parseInt(req.getParameter("attraction3"));
		int[] idAttractions = {attraction1, attraction2, attraction3}; 
		Attraction[] attractions = new Attraction[idAttractions.length];
		for (int i = 0; i < idAttractions.length; i++) {
		for (Attraction attraction : attractionService.list()) {
				if (attraction.getId() == Integer.valueOf(idAttractions[i])) {
					attractions[i] = attraction;
				}
			}
		}
		promotionService.create(name, type, attractions);

		resp.sendRedirect("/turismo/promotions/index.do");
	}

}
