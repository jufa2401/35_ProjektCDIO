package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.BreweryDAO;
import boundary.dao.BreweryDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.BreweryDTO;
public class MySQLBreweryDAO implements BreweryDAO {
	public ArrayList<BreweryDTO> getBrewery() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<BreweryDTO> Brewerylist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("fængsel");
				int price = rs.getInt("Kolonne hvor pris er");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				
				BreweryDTO DTO = new BreweryDTO(fieldnumber,color,price);
				Brewerylist.add(DTO);
			}
			return Brewerylist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}