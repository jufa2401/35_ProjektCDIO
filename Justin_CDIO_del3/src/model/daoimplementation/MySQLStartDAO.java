package model.daoimplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.daoboundary.StartDAO;
import model.fieldclasses.StartDTO;;

public class MySQLStartDAO implements StartDAO {
	@Override
	public StartDTO getStart(int id, String name) throws RuntimeException {

		Connector c = new Connector();
		PreparedStatement preparedstatement = null;
		
		int bonus = 0;
		
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(17);

		try {
			preparedstatement = c.prep(query);
			preparedstatement.setInt(1, id);
			ResultSet rs = preparedstatement.executeQuery();
			while (rs.next()) {
			bonus = rs.getInt("bonus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		StartDTO startfield = new StartDTO(id, name, null, bonus);
		return startfield;
	}
}



