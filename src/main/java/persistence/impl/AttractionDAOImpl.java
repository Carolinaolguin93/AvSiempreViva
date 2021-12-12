package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Attraction;
import persistence.AttractionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AttractionDAOImpl implements AttractionDAO {

	public ArrayList<Attraction> findAll() {
		try {
			String sql = "SELECT * FROM ATTRACTION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Attraction> attractions = new ArrayList<Attraction>();
			while (resultados.next()) {
				attractions.add(toAttraction(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Attraction find(Integer id) {
		try {
			String sql = "SELECT * FROM ATTRACTION WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Attraction attraction = null;
			if (resultados.next()) {
				attraction = toAttraction(resultados);
			}

			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Attraction toAttraction(ResultSet attractionRegister) throws SQLException {
		return new Attraction(attractionRegister.getInt(1), attractionRegister.getString(2),
					attractionRegister.getDouble(3), attractionRegister.getDouble(4), attractionRegister.getInt(5), attractionRegister.getString(6));
	}

	@Override
	public int insert(Attraction attraction) {
		try {
			String sql = "INSERT INTO ATTRACTION (NAME, COST, DURATION, CAPACITY, FK_TYPE_OF_ATTR) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
			statement.setString(i++, attraction.getType());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Attraction attraction) {
		try {
			String sql = "UPDATE ATTRACTION SET NAME = ?, COST = ?, DURATION = ?, CAPACITY = ?, FK_TYPE_OF_ATTR = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
			statement.setString(i++, attraction.getType());
			statement.setInt(i++, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Attraction attraction) {
		try {
			String sql = "DELETE FROM ATTRACTION WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATTRACTION";
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



}
