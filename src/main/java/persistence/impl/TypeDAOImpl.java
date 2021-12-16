package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.User;
import model.nullobjects.NullUser;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class TypeDAOImpl{

	public int insert(User user) {
		try {
			String sql = "INSERT INTO USERS (USERNAME, PASSWORD, COINS, TIME, ADMIN, FK_TIPODEATRACCION) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setDouble(3, user.getCoins());
			statement.setDouble(4, user.getTime());
			statement.setBoolean(5, user.getAdmin());
			statement.setString(6, user.getType());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(User user) {
		try {
			String sql = "UPDATE USERS SET COINS = ?, TIME = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, user.getCoins());
			statement.setDouble(2, user.getTime());
			statement.setDouble(3, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(User user) {
		try {
			String sql = "DELETE FROM USERS WHERE USERNAME = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public User findByUsername(String username) {
		try {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			User user = NullUser.build();

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USERS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public LinkedList<String> findAll() {
		try {
			String sql = "SELECT * FROM type_of_attr";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<String> tipos = new LinkedList<String>();
			while (resultados.next()) {
				tipos.add(resultados.getString(1));
			}

			return tipos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private User toUser(ResultSet userRegister) throws SQLException {
		return new User(userRegister.getInt(1), userRegister.getString(2), userRegister.getString(3),
				userRegister.getDouble(4), userRegister.getDouble(5), userRegister.getBoolean(6), userRegister.getString(7));
	}

	public int updatePsw(User user) {
		try {
			String sql = "UPDATE USERS SET password = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setDouble(2, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
}
