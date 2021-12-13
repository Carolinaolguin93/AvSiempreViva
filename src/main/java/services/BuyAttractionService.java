package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Attraction;
import model.Sugerencia;
import model.User;
import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItinerarioDAO;

public class BuyAttractionService {
	ItinerarioDAO itinerario = new ItinerarioDAO();
	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Attraction attraction = attractionDAO.find(attractionId);
		
		ArrayList<Sugerencia> comprasRealizadas = itinerario.find(userId);
		System.out.println(comprasRealizadas);

		if (!attraction.canHost()) {
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}if(comprasRealizadas.contains(attraction)) { 
			errors.put("user", "Ya compraste esta actividad");
		}

		if (errors.isEmpty()) {
			user.buyActivity(attraction);
			attraction.host();		
			try {
				itinerario.insert(user, attraction);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			attractionDAO.update(attraction);
			userDAO.update(user);
		}

		return errors;

	}

}
