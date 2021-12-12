package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import model.Attraction;
import model.Promotion;
import model.Sugerencia;
import model.User;
import persistence.AttractionDAO;
import persistence.PromotionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class ItinerarioDAO {

	public int insert(User usuario, Sugerencia sugerencia) throws SQLException {
		String sql = "INSERT INTO itinerary_users (id_user, id_attraction, id_promotion) VALUES (?,?,?)";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setInt(1, usuario.getId());
		if(sugerencia.esPromocion()) {
			statement.setNull(2, Types.INTEGER);
			statement.setInt(3, sugerencia.getId());

		}else {
			statement.setNull(3, Types.INTEGER);
			statement.setInt(2, sugerencia.getId());
		}
		
		int rows = statement.executeUpdate();
		
		return rows;
	}	
	
	public ArrayList<Sugerencia> find(Integer id) {
		try {
			String sql = "SELECT * FROM ITINERARY_USERS WHERE itinerary_users.id_user = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			AttractionDAO attractionDao = DAOFactory.getAttractionDAO();
			PromotionDAO promotionDao = DAOFactory.getPromotionDAO();
			ArrayList<Sugerencia> listaDeCompras = new ArrayList<Sugerencia>();
			while (resultados.next()) {
				if(resultados.getInt(3) == 0) {
					Attraction atr = attractionDao.find(resultados.getInt(2));
						listaDeCompras.add(atr);
				}else {
					Promotion promo = promotionDao.find(resultados.getInt(3));
						listaDeCompras.add(promo);
				}
			}

			return listaDeCompras;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
}