/*
 * 
 */
package model.daoimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.PlayerList;
import model.daoboundary.PlayerDAO;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse henter spillere fra databasen
 */
public class MySQLPlayerDAO implements PlayerDAO {

	/**
	 * Denne metode henter antal spillere fra databasen med en SELECT COUNT(*) skrevet i sql.txt
	 *
	 * @return the player count
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public int getPlayerCount() throws RuntimeException {
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(4);

		try {
			ResultSet rs = c.doQuery(query);
			rs.next();
			int playerCount = rs.getInt(1);

			return playerCount;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}

	}

	/* (non-Javadoc)
	 * @see boundary.dao.PlayerDAO#getPlayers()
	 */
	@Override
	public PlayerList getPlayers() throws RuntimeException {
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(2);
		int playerCount = getPlayerCount();
		PlayerList pl = new PlayerList(playerCount);
		try {
			ResultSet rs = c.doQuery(query);
			while (rs.next()) {
				int playerid = rs.getInt("player_id");
				String name = rs.getString("name");
				int currentField = rs.getInt("position");
				int balance = rs.getInt("balance");
				int roundsleftjail = rs.getInt("rounds_left_jail");
				pl.addPlayer(playerid, name, currentField, balance, roundsleftjail);
			}
			return pl;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}
