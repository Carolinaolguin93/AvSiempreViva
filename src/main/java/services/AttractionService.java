package services;

import java.util.List;

import model.Attraction;
import persistence.AttractionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Attraction> list() {
		return DAOFactory.getAttractionDAO().findAll();
	}

	public Attraction create(String name, Double cost, Double duration, Integer capacity, String type) {

		Attraction attraction = new Attraction(-1, name, cost, duration, capacity, type);

		if (attraction.isValid()) {
			AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
		}

		return attraction;
	}

	public Attraction update(Integer id, String name, Double cost, Double duration, Integer capacity, String type) {

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		Attraction attraction = attractionDAO.find(id);

		attraction.setName(name);
		attraction.setCost(cost);
		attraction.setDuration(duration);
		attraction.setCapacity(capacity);
		attraction.setType(type);;


		if (attraction.isValid()) {
			attractionDAO.update(attraction);
		}

		return attraction;
	}

	public void delete(Integer id) {
		Attraction attraction = new Attraction(id, null, null, null, null, null);

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}

	public Attraction find(Integer id) {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		return attractionDAO.find(id);
	}

}
