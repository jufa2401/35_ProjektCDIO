package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.StreetDAO;
import boundary.dao.StreetDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.StreetDTO;
public class MySQLStreetDAO implements StreetDAO {
	public ArrayList<StreetDTO> getStreet() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<StreetDTO> Streetlist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("fængsel");
				int price = rs.getInt("Kolonne hvor price er i");
				int rent= rs.getInt("Kolonne hvor rent er i");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				
				StreetDTO DTO = new StreetDTO(fieldnumber,color,price,rent);
				Streetlist.add(DTO);
			}
			return Streetlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}