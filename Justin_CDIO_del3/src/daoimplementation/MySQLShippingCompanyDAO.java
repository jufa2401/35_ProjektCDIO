package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boundary.dao.ShippingCompanyDAO;
import boundary.dao.ShippingCompanyDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.ShippingCompanyDTO;
public class MySQLShippingCompanyDAO implements ShippingCompanyDAO {
	public ArrayList<ShippingCompanyDTO> getShippingCompany() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<ShippingCompanyDTO> ShippingCompanylist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				int fieldnumber = rs.getInt("fængsel");
//				Henter int (hex) værdien for en farve for et givet felt
				int colorint = rs.getInt("color");
//				Konverterer farveintegeren om til en reel farve
				Color color = new Color(colorint);
				int price = rs.getInt(3);
				
				ShippingCompanyDTO DTO = new ShippingCompanyDTO(fieldnumber,color, price);
				ShippingCompanylist.add(DTO);
			}
			return ShippingCompanylist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
}