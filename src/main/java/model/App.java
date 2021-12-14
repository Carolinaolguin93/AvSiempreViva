package model;

import persistence.commons.DAOFactory;
import services.PromotionService;

public class App {

	public static void main(String[] args) {
		
		
		Integer ultimoIdPromo = DAOFactory.getPromotionDAO().countAll();
		Integer nuevoIdPromo = ultimoIdPromo++;
		System.out.println(nuevoIdPromo);
	}
	
	
	    
}

	

