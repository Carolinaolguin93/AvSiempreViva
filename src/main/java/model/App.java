package model;

import services.PromotionService;

public class App {

	public static void main(String[] args) {
		PromotionService promotionService = new PromotionService();
		//System.out.println(promotionService.find(7));
		System.out.println(promotionService.find(1));
		
	}
	
}

	

