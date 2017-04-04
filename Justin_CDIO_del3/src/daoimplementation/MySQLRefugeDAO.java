package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.RefugeDAO;
import boundary.dao.RefugeDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.RefugeDTO;
public class MySQLRefugeDAO implements RefugeDAO {
	public ArrayList<RefugeDTO> getRefuge() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<RefugeDTO> Refugelist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("fængsel");
				int bonus = rs.getInt("Kolonne hvor bonus er");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				
				RefugeDTO DTO = new RefugeDTO(fieldnumber,color, bonus);
				Refugelist.add(DTO);
			}
			return Refugelist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}
