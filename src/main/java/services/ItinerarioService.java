package services;

import java.util.ArrayList;

import model.Sugerencia;
import persistence.impl.ItinerarioDAO;

public class ItinerarioService {
	
	public ArrayList<Sugerencia> find(Integer id) {
		ItinerarioDAO itinerario = new ItinerarioDAO();
		return itinerario.find(id);
	}
}
