/*
 * 
 */
package model.daoimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.ChanceCard;
import model.daoboundary.ChanceDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQLChanceCardDAO.
 */
public class MySQLChanceCardDAO implements ChanceDAO {

	/* (non-Javadoc)
	 * @see boundary.dao.ChanceDAO#getChance()
	 */
	@Override
	public ChanceCard getChance() throws RuntimeException {
		Connector c = new Connector();
		int id = -1;
		int type = 0;
		String text = null;
		int move_relative = 0;
		int move_absolute = 0;
		int pay_1 = 0;
		int pay_2 = 0;
		double r = Math.random() * getChanceCardCount();
		int ri = (int) r;
		// /* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(13);
		String newquery = query.replace("##", "" + ri);
		try {
			ResultSet rs = c.doQuery(newquery);
			while (rs.next()) {
				id = rs.getInt(1);
				type = rs.getInt(2);
				text = rs.getString(3);
				move_relative = rs.getInt(4);
				move_absolute = rs.getInt(5);
				pay_1 = rs.getInt(6);
				pay_2 = rs.getInt(7);
			}
			ChanceCard chancecard = new ChanceCard(id, type, text, move_relative, move_absolute, pay_1, pay_2);
			return chancecard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

	/* (non-Javadoc)
	 * @see boundary.dao.ChanceDAO#getChanceCardCount()
	 */
	@Override
	public int getChanceCardCount() throws RuntimeException {
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(12);
		try {
			ResultSet rs = c.doQuery(query);
			rs.next();
			int chanceCount = rs.getInt(1);
			return chanceCount;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}
