package model.daoimplementation;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.daoboundary.StreetDAO;
import model.fieldclasses.StreetDTO;

public class MySQLStreetDAO implements StreetDAO {
	@Override
	public StreetDTO getStreet(int id, String name, int price, int[] rent) throws RuntimeException {

		Connector c = new Connector();
		PreparedStatement preparedstatement = null;
		
		String streetgroup = null;
		int colorint = 0;
		Color color = null;
		int houseprice = 0;
		
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(15);

		try {
			preparedstatement = c.prep(query);
			preparedstatement.setInt(1, id);
			ResultSet rs = preparedstatement.executeQuery();
			while (rs.next()) {
				streetgroup = rs.getString("street_group");
				colorint = rs.getInt("dec_color");
				houseprice = rs.getInt("house_price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		color = new Color(colorint);
		StreetDTO streetfield = new StreetDTO(id, name, streetgroup, color, price, rent, houseprice);
		return streetfield;
	}
}
