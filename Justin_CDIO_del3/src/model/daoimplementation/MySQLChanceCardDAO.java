/*
 * 
 */
package model.daoimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.NOTIMPLEMENTEDChanceCard;
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
	public NOTIMPLEMENTEDChanceCard getChance() throws RuntimeException {
		final Connector C = new Connector();
		int id = -1;
		int type = 0;
		String text = null;
		int move_relative = 0;
		int move_absolute = 0;
		int pay_1 = 0;
		int pay_2 = 0;
		final double r = Math.random() * getChanceCardCount();
		final int ri = (int) r;
		// /* Alt SQL er holdt ude af java koden */
		final SQLMapper m = new SQLMapper();
		final String query = m.getStatement(13);
		final String newquery = query.replace("##", "" + ri);
		try {
			final ResultSet rs = C.doQuery(newquery);
			while (rs.next()) {
				id = rs.getInt(1);
				type = rs.getInt(2);
				text = rs.getString(3);
				move_relative = rs.getInt(4);
				move_absolute = rs.getInt(5);
				pay_1 = rs.getInt(6);
				pay_2 = rs.getInt(7);
			}
			final NOTIMPLEMENTEDChanceCard chancecard = new NOTIMPLEMENTEDChanceCard(id, type, text, move_relative, move_absolute, pay_1, pay_2);
			return chancecard;
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

	/* (non-Javadoc)
	 * @see boundary.dao.ChanceDAO#getChanceCardCount()
	 */
	@Override
	public int getChanceCardCount() throws RuntimeException {
		final Connector C = new Connector();
		/* Alt SQL er holdt ude af java koden */
		final SQLMapper m = new SQLMapper();
		final String query = m.getStatement(12);
		try {
			final ResultSet rs = C.doQuery(query);
			rs.next();
			final int chanceCount = rs.getInt(1);
			return chanceCount;

		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}
