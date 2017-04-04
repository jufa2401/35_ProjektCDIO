package daoimplementation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.PlayerDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.PlayerDTO;
public class MySQLPlayerDAO implements PlayerDAO {
	
	public ArrayList<PlayerDTO> getPlayer() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<PlayerDTO> playerlist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				int id = rs.getInt(1);
				PlayerDTO DTO = new PlayerDTO(name, balance);
				playerlist.add(DTO);
			}
			return playerlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

}

