package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class TypeDAOImpl {

	public int insert(String tipo) {
		try {
			String sql = "INSERT INTO type_of_attr  (Tipo) VALUES (?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipo);

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public String findByName(String name) {
		try {
			String sql = "SELECT * FROM type_of_attr WHERE Tipo = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();
			
			String tipo = null;
			if (resultados.next()) {
				tipo = resultados.getString(1);
			}

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM type_of_attr";
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

	public List<String> findAll() {
		try {
			String sql = "SELECT * FROM type_of_attr";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<String> tipos = new LinkedList<String>();
			while (resultados.next()) {
				tipos.add(resultados.getString(1));
			}

			return tipos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int update(String name) {
		try {
			String sql = "UPDATE type_of_attr SET tipo = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(String name) {
		try {
			String sql = "DELETE FROM type_of_attr WHERE tipo = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
