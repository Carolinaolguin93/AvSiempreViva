package services;

import java.util.List;

import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.TypeDAOImpl;

public class TypeService {
	
	private TypeDAOImpl types = new TypeDAOImpl();

	public List<String> list() {
		
		return types.findAll();
	}

	public String create(String name) {
		
		String type = name;
		types.insert(type);
			
		return type;
	}

	public String findByName(String name) {
		
		return types.findByName(name);
	}
	
	public String update(String name) {
		
		String type = name;

		types.update(type);
		return type;
	}
	
	public String delete(String name) {
		
		String type = name;
		
		types.delete(type);
			
		return type;
	}
}
