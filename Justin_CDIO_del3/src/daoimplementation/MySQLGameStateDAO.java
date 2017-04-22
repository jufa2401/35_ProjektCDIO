//sasasa
package daoimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import boundary.dao.GameStateDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.PlayerList;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.StreetDTO;

public class MySQLGameStateDAO implements GameStateDAO {
	
	public void saveFieldStatus(GameBoardDTO gb) throws RuntimeException {
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(7);
		
		try {
			for (int i = 0; i < gb.getNumberOfFields(); i++) {
				
				int ownerid = -1;
				//Hvis ownerid ændres til INTEGER kan den være NULL
				
				int houses = 0;
				int hotels = 0;
				FieldDTO field = gb.getField(i);
				int type = field.getType();
				switch (type) {
				case 1:
				case 2:
				case 5:
					Ownable ofield = (Ownable) field;
					PlayerDTO owner = ofield.getOwner();
					ownerid = (owner != null) ? owner.getPlayerID() : -1;
					if (type == 5) {
						StreetDTO sfield = (StreetDTO) gb.getField(i);
						houses = sfield.getHouses();
						hotels = sfield.getHotels();
					}
					break;
				default:
					break;
				}
				// String newquery = query +ownerid
				String newquery = query + i + "," + ownerid + "," + houses + "," + hotels + ");";
				
//				Gemmer kun felter som er ejet
				if(ownerid >= 0) {
				c.doUpdate(newquery); 	
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
		
	}

	public void emptyDataEntries() throws RuntimeException {
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(8);
		String query2 = m.getStatement(9);
		try {
			
			c.doUpdate(query2);
			c.doUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

	public void savePlayerStatus(PlayerList p) throws RuntimeException {
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		int nplayers = p.getNumberofPlayer();
		String query = m.getStatement(10);
		
		try {

			for (int i = 0; i < nplayers; i++) {
				PlayerDTO player = p.getPlayer(i);
				String name = player.getName();
				int balance = player.getBalance();
				int roundsjail = player.getRoundsLeftJail();
				int position = player.getCurrentField();
				//Her kunne man have brugt Preparedstatement
				String newquery = query + i + ",'" + name + "'," + position + "," + balance + "," + roundsjail + ");";
				c.doUpdate(newquery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

	@Override
	public void loadFieldStatus(PlayerList pl, GameBoardDTO gb) throws RuntimeException {
		Connector c = new Connector();
		int count = 0; // count er blevet lavet af debugging hensyn
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(11);
		try {
			ResultSet rs = c.doQuery(query);
			while (rs.next()) {
				int fieldid = rs.getInt(1);
				int ownerid = rs.getInt(2); //check
				int houses = rs.getInt(3);
				// int hotels = rs.getInt(4);
				FieldDTO field = gb.getField(fieldid);
				int type = field.getType();
				if (ownerid >= 0) {
					Ownable ofield = (Ownable) field;
					PlayerDTO p = pl.getPlayer(ownerid);
					ofield.setOwner(p);
					if (type == 5) {
						StreetDTO sfield = (StreetDTO) gb.getField(fieldid);
						sfield.setHouses(houses);
//					    sfield.setHotels(hotels);
					}
				}
				++count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

	@Override
	public boolean checkFieldStatus() throws RuntimeException {
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(14);
		try {
			ResultSet rs = c.doQuery(query);
			rs.next();
			int fieldstatuscount = rs.getInt(1);

			if (fieldstatuscount > 0) {
				return true;
			}
		}
	
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error"); 
		}

		return false;
	
	}

	
}