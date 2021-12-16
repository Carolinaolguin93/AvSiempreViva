package controller.promotion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromotionService;

@WebServlet("/promotions/delete.do")
public class DeleteAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = -239322921312414248L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		promotionService.delete(id);

		resp.sendRedirect("/turismo/promotions/index.do");
	}
}