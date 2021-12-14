package model;

import services.AttractionService;

public class App {

	public static void main(String[] args) {
		
		AttractionService ps = new AttractionService();
		
		for(Attraction promo : ps.list()) {
			System.out.println(promo.getName());
		}
		
	
	}
	
	
	    
}

	

