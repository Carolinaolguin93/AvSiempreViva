package persistence;

import java.util.List;

import model.Attraction;
import model.Promotion;
import persistence.commons.GenericDAO;

public interface PromotionDAO extends GenericDAO<Promotion> {

	public List<Promotion> findAll(List<Attraction> listAttr);

	public int insertAttr_Promotion(Integer idpromo, Attraction attraction);

	int deleteAttr_Promotion(Promotion promotion);

}