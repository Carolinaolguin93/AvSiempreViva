package services;

import java.util.List;

import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class UserService {

	public List<User> list() {
		return DAOFactory.getUserDAO().findAll();
	}

	public User createUser(String username, String password, Double coins, Double time, Boolean admin, String type) {

		User user = new User(-1, username, password, coins, time, admin, type);

		if (user.isValid()) {
			UserDAO userDAO = DAOFactory.getUserDAO();
			userDAO.insert(user);
		}

		return user;
	}

	public User update(Integer id, String username, String password, Double coins, Double time, Boolean admin, String type) {

		UserDAO userDAO = DAOFactory.getUserDAO();
		User user = userDAO.find(id);

		user.setUsername(username);
		user.setPassword(password);
		user.setCoins(coins);
		user.setTime(time);
		user.setAdmin(admin);
		user.setType(type);


		if (user.isValid()) {
			userDAO.update(user);
		}

		return user;
	}

	public void delete(Integer id) {
		User user = new User(id, null, null, null, null, null, null);

		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.delete(user);
	}

	public User find(Integer id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		return userDAO.find(id);
	}

}
