package daoimplementation;
import java.sql.ResultSet;
import java.sql.SQLException;

import boundary.dao.PlayerDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.PlayerList;
public class MySQLPlayerDAO implements PlayerDAO {
	
	public PlayerList getPlayers() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(2);
		int playerCount = getPlayerCount();
		PlayerList pl = new PlayerList(playerCount);
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int playerid = rs.getInt(1);
				String name = rs.getString(2);
				int currentField = rs.getInt(3);
				int balance = rs.getInt(4);
				int roundsleftjail = rs.getInt(5);
				pl.addPlayer(playerid, name, currentField, balance, roundsleftjail);
			}
			return pl;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
	
	public int getPlayerCount() throws RuntimeException{
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
}

