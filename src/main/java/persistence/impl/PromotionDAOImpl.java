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
			String sql = "SELECT promotion.*, group_concat(fk_attraction, ',') AS 'list_atr' FROM promotion  JOIN attraction_promotion ON attraction_promotion.fk_promotion = promotion.id  GROUP BY promotion.id";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promotion> promotion = new LinkedList<Promotion>();
			while (resultados.next()) {
				int id = resultados.getInt("id");
				String name = resultados.getString("name");
				String type = resultados.getString("fk_type_of_attr");
				String[] attractionString = resultados.getString("list_atr").split(",");
				Attraction[] attractions = new Attraction[attractionString.length];
				for (int i = 0; i < attractionString.length; i++) {
					for (Attraction attraction : atr) {
						if (attraction.getId() == Integer.valueOf(attractionString[i])) {
							attractions[i] = attraction;
						}
					}
				}
				if (resultados.getInt("id") == 1) {
					promotion.add(new PromocionAbsoluta(id, name, type, attractions));
				} else if (resultados.getInt("id") == 2) {
					promotion.add(new PromocionTresPorDos(id, name, type, attractions));
				} else if (resultados.getInt("id") == 3) {
					promotion.add(new PromocionPorcentual(id, name, type, attractions));
				}
			}
			return promotion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promotion find(Integer id) {
		try {
			String sql = "SELECT * FROM (SELECT promotion.*, group_concat(fk_attraction, ',') AS 'list_atr' FROM promotion  JOIN attraction_promotion ON attraction_promotion.fk_promotion = promotion.id  GROUP BY promotion.id) WHERE id = ?";
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
		String[] attractionString = promotionRegister.getString(4).split(",");
		Attraction[] attractions = new Attraction[attractionString.length];
		for (int i = 0; i < attractionString.length; i++) {
			for (Attraction attraction : DAOFactory.getAttractionDAO().findAll()) {
				if (attraction.getId() == Integer.valueOf(attractionString[i])) {
					attractions[i] = attraction;
				}
			}
		}
		Promotion promo = null;
		if (promotionRegister.getInt(1) == 1) {
			promo = new PromocionAbsoluta(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), attractions);
		} else if (promotionRegister.getInt(1) == 2) {
			promo = new PromocionTresPorDos(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), attractions);
		} else if (promotionRegister.getInt(1) == 3) {
			promo = new PromocionPorcentual(promotionRegister.getInt(1), promotionRegister.getString(2),
					promotionRegister.getString(3), attractions);
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
	public int insert(Promotion promotion) {
		try {
			String sql = "INSERT INTO PROMOTION (NAME, FK_TYPE_OF_ATTR) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promotion.getName());
			statement.setString(1, promotion.getType());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int insertAttr_Promotion(Promotion promotion, Attraction attraction) {
		try {
			String sql = "INSERT INTO ATTRACTION_PROMOTION (FK_ATTRACTION , FK_PROMOTION) VALUES (?, ?);";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			statement.setInt(2, promotion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promotion promotion) {
		try {
			String sql = "UPDATE PROMOTION SET NAME = ?, FK_TYPE_OF_ATTR = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promotion.getName());
			statement.setString(2, promotion.getType());
			statement.setInt(3, promotion.getId());
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
