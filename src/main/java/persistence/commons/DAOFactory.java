package persistence.commons;

import persistence.AttractionDAO;
import persistence.PromotionDAO;
import persistence.UserDAO;
import persistence.impl.AttractionDAOImpl;
import persistence.impl.PromotionDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOImpl();
	}
	
	public static PromotionDAO getPromotionDAO() {
		return new PromotionDAOImpl();
	}
	
}
