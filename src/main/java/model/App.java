package model;

import persistence.impl.TypeDAOImpl;
import services.PromotionService;

public class App {

	public static void main(String[] args) {
		TypeDAOImpl promotionService = new TypeDAOImpl();
		//System.out.println(promotionService.find(7));
		System.out.println(promotionService.findAll());
		
	}
	
}

	

