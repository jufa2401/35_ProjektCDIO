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

		final Connector M = new Connector();
		PreparedStatement preparedstatement = null;
		
		int bonus = 0;
		
		final SQLMapper m = new SQLMapper();
		final String query = m.getStatement(17);

		try {
			preparedstatement = M.prep(query);
			preparedstatement.setInt(1, id);
			ResultSet rs = preparedstatement.executeQuery();
			while (rs.next()) {
			bonus = rs.getInt("bonus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

		final StartDTO startfield = new StartDTO(id, name, null, bonus);
		return startfield;
	}
}



