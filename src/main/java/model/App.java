package model;

import java.util.ArrayList;

import persistence.AttractionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.AttractionDAOImpl;
import services.ItinerarioService;

public class App {

	public static void main(String[] args) {
		ItinerarioService itinerarioService = new ItinerarioService();
		ArrayList<Sugerencia> itinerario = itinerarioService.find(4);
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		System.out.println(itinerario);
		System.out.println(itinerario.contains(attractionDAO.find(1)));
		System.out.println(attractionDAO.find(1));
		System.out.println(itinerario);
	
	}
	
	
	    
}

	

