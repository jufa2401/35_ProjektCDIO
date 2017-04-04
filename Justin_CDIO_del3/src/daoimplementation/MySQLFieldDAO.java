package daoimplementation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Virker nok ikke, da field er abstract
import boundary.dao.FieldDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.fieldclasses.FieldDTO;
public class MySQLFieldDAO implements FieldDAO {
	public ArrayList<FieldDTO> getField() throws RuntimeException{
		Connector c = new Connector();

		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(1);
		
		ArrayList<FieldDTO> fieldlist = new ArrayList<>();
		try {
			ResultSet rs = c.doQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				int id = rs.getInt(1);
				FieldDTO DTO = new FieldDTO();
				fieldlist.add(DTO);
			}
			return fieldlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}

}




