
package services;

import java.util.Collections;
import java.util.List;
import model.Attraction;
import model.OrdenarParaSugerir;
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

	public Promotion create(String name, String type, String typePromo,  Attraction[] attractions) {

		Promotion promo = null;
		Integer ultimoIdPromo = DAOFactory.getPromotionDAO().ultimoIdTabla();
		Integer nuevoIdPromo = ultimoIdPromo+1;
		if (typePromo.equals("Absoluta")) {
			promo = new PromocionAbsoluta(nuevoIdPromo, name, type, typePromo, attractions);
		} else if (typePromo.equals("TresPorDos")) {
			promo = new PromocionTresPorDos(nuevoIdPromo, name, type, typePromo, attractions);
		} else if (typePromo.equals("Porcentual")) {
			promo = new PromocionPorcentual(nuevoIdPromo, name, type, typePromo, attractions);
		}
		
		DAOFactory.getPromotionDAO().insert(promo);
		for(Attraction attr : promo.getAttractions()) {
			DAOFactory.getPromotionDAO().insertAttr_Promotion(nuevoIdPromo, attr);
		}
		
		return promo;

	}
	
	public void listOrdenada(List<Promotion> promotions, String tipo) {
		Collections.sort(promotions, new OrdenarParaSugerir(tipo));
	} 
	
	public Promotion update(Integer id, String name, String type, Attraction [] attractions, String typePromo) {

		PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
		Promotion promotion = promotionDao.find(id);

		promotion.setName(name);
		promotion.setType(type);
		promotion.setTypePromo(typePromo);

		
		DAOFactory.getPromotionDAO().deleteAttr_Promotion(promotion);
		for(Attraction attr : attractions) {
			DAOFactory.getPromotionDAO().insertAttr_Promotion(id, attr);
		}
		promotionDao.update(promotion);

		return promotion;
	}

	public void delete(Integer id) {
		PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
		Promotion promo = promotionDao.find(id);
		
		promotionDao.deleteAttr_Promotion(promo);
		promotionDao.delete(promo);
		
	}

	public Promotion find(Integer id) {
		PromotionDAO promotionDAO = DAOFactory.getPromotionDAO();
		return promotionDAO.find(id);
	}

}
