//Skal måske ikke implementeres

package daoimplementation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Connector;
import controller.SQLMapper;
import entity.GameBoardDTO;
public class MySQLGameBoardDAO {
	public ArrayList<GameBoardDTO> getField() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<GameBoardDTO> fieldlist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				int id = rs.getInt(1);
				GameBoardDTO DTO = new GameBoardDTO();
				fieldlist.add(DTO);
			}
			return fieldlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

}

