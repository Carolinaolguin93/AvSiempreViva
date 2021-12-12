package services;

import java.util.List;

import model.Attraction;
import model.PromocionAbsoluta;
import model.PromocionPorcentual;
import model.PromocionTresPorDos;
import model.Promotion;
import persistence.AttractionDAO;
import persistence.PromotionDAO;
import persistence.commons.DAOFactory;

public class PromotionService {

	public List<Promotion> list() {
		AttractionDAO attractionDao = DAOFactory.getAttractionDAO();
		List<Attraction> listAttr = attractionDao.findAll();
		return DAOFactory.getPromotionDAO().findAll(listAttr);
	}

	public Promotion create(String name, String type, Attraction[] attractions) {

		Promotion promo = null;
		if (type.equals("Aventura")) {
			promo = new PromocionAbsoluta(-1, name, type, attractions);
		} else if (type.equals("Visita_Guiada")) {
			promo = new PromocionTresPorDos(-1, name, type, attractions);
		} else if (type.equals("Gastronomia")) {
			promo = new PromocionPorcentual(-1, name, type, attractions);
		}
		
		for(Attraction attr : attractions) {
			DAOFactory.getPromotionDAO().insertAttr_Promotion(promo, attr);
		}
		
		DAOFactory.getPromotionDAO().insert(promo);
		return promo;

	}

	public Promotion update(Integer id, String name, String type) {

		PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDao.find(id);

		promotion.setName(name);
		promotion.setType(type);;

		promotionDao.update(promotion);

		return promotion;
	}

	public void delete(Integer id) {
	
		Promotion promo = null;
		if (id == 1) {
			promo = new PromocionAbsoluta(id, null, null, null);
		} else if (id == 2) {
			promo = new PromocionTresPorDos(id, null, null, null);
		} else if (id == 3) {
			promo = new PromocionPorcentual(id, null, null, null);
		}
		
		PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
		promotionDao.delete(promo);
		promotionDao.deleteAttr_Promotion(promo);
		
	}

	public Promotion find(Integer id) {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		return promotionDAO.find(id);
	}

}
