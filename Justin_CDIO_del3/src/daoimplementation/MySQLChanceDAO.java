package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.ChanceDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.ChanceDTO;
public class MySQLChanceDAO implements ChanceDAO {
	public ArrayList<ChanceDTO> getChance() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<ChanceDTO> Chancelist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("fængsel");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				
				ChanceDTO DTO = new ChanceDTO(fieldnumber,color);
				Chancelist.add(DTO);
			}
			return Chancelist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}
