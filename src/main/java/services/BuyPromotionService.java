package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Promotion;
import model.Sugerencia;
import model.User;
import persistence.PromotionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAO;

public class BuyPromotionService {
	ItinerarioDAO itinerario = new ItinerarioDAO();
	PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer promotionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Promotion promotion = promotionDao.find(promotionId);
		
		ArrayList<Sugerencia> comprasRealizadas = itinerario.find(userId);
		System.out.println(comprasRealizadas);

		if (!promotion.canHost()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(promotion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(promotion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}if (!user.canAttend(promotion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}if(comprasRealizadas.contains(promotion)) { // NO FUNCIONA 
			errors.put("user", "Ya compraste esta actividad");
		}

		if (errors.isEmpty()) {
			user.buyActivity(promotion);
			promotion.host();		
			user.agregarSugerenciaAlItinerario(promotion);
			try {
				itinerario.insert(user, promotion);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("t o f:"+comprasRealizadas.contains(promotion));
			promotionDao.update(promotion);
			userDAO.update(user);
		}

		return errors;

	}

}
