package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Attraction;
import model.PromocionAbsoluta;
import model.PromocionPorcentual;
import model.PromocionTresPorDos;
import model.Promotion;
import persistence.PromotionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromotionDAOImpl implements PromotionDAO {

	public List<Promotion> findAll(List<Attraction> atr) {
		try {
			String sql = "SELECT promotion.*, group_concat(fk_attraction, ',') AS 'list_atr' FROM promotion"
					+ " JOIN attraction_promotion ON attraction_promotion.fk_promotion = promotion.id  GROUP BY promotion.id";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promotion> promotion = new LinkedList<Promotion>();
			while (resultados.next()) {
				int id = resultados.getInt("id");
				String name = resultados.getString("name");
				String type = resultados.getString("fk_type_of_attr");
				String typePromo = resultados.getString("type_promotion");
				String[] attractionString = resultados.getString("list_atr").split(",");
				Attraction[] attractions = new Attraction[attractionString.length];
				for (int i = 0; i < attractionString.length; i++) {
					for (Attraction attraction : atr) {
						if (attraction.getId() == Integer.valueOf(attractionString[i])) {
							attractions[i] = attraction;
						}
					}
				}
				if (typePromo.equals("Absoluta")) {
					promotion.add(new PromocionAbsoluta(id, name, type, typePromo, attractions));
				} else if (typePromo.equals("TresPorDos")) {
					promotion.add(new PromocionTresPorDos(id, name, type, typePromo, attractions));
				} else if (typePromo.equals("Porcentual")) {
					promotion.add(new PromocionPorcentual(id, name, type, typePromo, attractions));
				}
			}
			return promotion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<String> listNamePromotions() {
		try {
			String sql = "SELECT promotion.name FROM promotion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<String> nombresPromos = new LinkedList<String>();
			while (resultados.next()) {
				nombresPromos.add(resultados.getString(1));
			}

			return nombresPromos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	@Override
	public Promotion find(Integer id) {
		try {
			String sql = "SELECT * FROM (SELECT promotion.*, group_concat(fk_attraction, ',') AS 'list_atr' "
					+ "FROM promotion  JOIN attraction_promotion ON attraction_promotion.fk_promotion = promotion.id  GROUP BY promotion.id) WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Promotion promo = null;
			if (resultados.next()) {
				promo = toPromotion(resultados);
			}

			return promo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promotion toPromotion(ResultSet promotionRegister) throws SQLException {
		String[] attractionString = promotionRegister.getString(5).split(",");
		Attraction[] attractions = new Attraction[attractionString.length];
		for (int i = 0; i < attractionString.length; i++) {
			for (Attraction attraction : DAOFactory.getAttractionDAO().findAll()) {
				if (attraction.getId() == Integer.valueOf(attractionString[i])) {
					attractions[i] = attraction;
				}
			}
		}
		Promotion promo = null;
		if (promotionRegister.getString(4).equals("Absoluta")) {
			promo = new PromocionAbsoluta(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), promotionRegister.getString(4), attractions);
		} else if (promotionRegister.getString(4).equals("TresPorDos")) {
			promo = new PromocionTresPorDos(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), promotionRegister.getString(4), attractions);
		} else if (promotionRegister.getString(4).equals("Porcentual")) {
			promo = new PromocionPorcentual(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), promotionRegister.getString(4), attractions);
		}
		return promo;
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOTION";
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
	

	@Override
	public int ultimoIdTabla() {
		try {
			String sql = "SELECT MAX(id) AS id FROM promotion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("id");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int insert(Promotion promotion) {
		try {
			String sql = "INSERT INTO PROMOTION (ID, NAME, FK_TYPE_OF_ATTR, TYPE_PROMOTION) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promotion.getId());
			statement.setString(2, promotion.getName());
			statement.setString(3, promotion.getType());
			statement.setString(4, promotion.getTypePromo());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int insertAttr_Promotion(Integer idpromo, Attraction attraction) {
		try {
			String sql = "INSERT INTO ATTRACTION_PROMOTION (FK_ATTRACTION , FK_PROMOTION) VALUES (?, ?);";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			statement.setInt(2, idpromo);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promotion promotion) {
		try {
			String sql = "UPDATE PROMOTION SET NAME = ?, FK_TYPE_OF_ATTR = ? , TYPE_PROMOTION = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promotion.getName());
			statement.setString(2, promotion.getType());
			statement.setString(3, promotion.getTypePromo());
			statement.setInt(4, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int deleteAttr_Promotion(Promotion promotion) {
		try {
			String sql = "DELETE FROM attraction_promotion WHERE attraction_promotion.fk_promotion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int delete(Promotion promotion) {
		try {
			String sql = "DELETE FROM PROMOTION WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


}
