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
import model.Promotion;
import model.User;
import services.PromotionService;

@WebServlet("/promotions/index.do")
public class ListPromotionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4651405914000213975L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promotion> promotions = promotionService.list();
		User user = (User) req.getSession().getAttribute("user");
		promotionService.listOrdenada(promotions, user.getType());
		req.setAttribute("promotions", promotions);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promotions/index.jsp");
		dispatcher.forward(req, resp);

	}
}