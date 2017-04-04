package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.JailDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.JailDTO;
public class MySQLJailDAO implements JailDAO {
	public ArrayList<JailDTO> getJail() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<JailDTO> Jaillist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("id");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				
				JailDTO DTO = new JailDTO(fieldnumber,color);
				Jaillist.add(DTO);
			}
			return Jaillist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}