package controller.type;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TypeService;

@WebServlet("/types/delete.do")
public class DeleteTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private TypeService typeService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.typeService = new TypeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");

		typeService.delete(type);

		resp.sendRedirect("/turismo/types/index.do");
	}


}