package controller.promotion;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/index.do")
public class ListPromotionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4651405914000213975L;
	private AttractionService attractionService;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Attraction> attractions = attractionService.list();
		req.setAttribute("attractions", attractions);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/index.jsp");
		dispatcher.forward(req, resp);

	}

}
