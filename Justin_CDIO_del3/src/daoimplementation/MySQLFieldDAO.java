package daoimplementation;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import boundary.dao.FieldDAO;
import controller.Connector;
import controller.SQLMapper;
import entity.PlayerDTO;
import entity.PlayerList;
import entity.fieldclasses.BreweryDTO;
import entity.fieldclasses.ChanceDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.JailDTO;
import entity.fieldclasses.Ownable;
import entity.fieldclasses.RefugeDTO;
import entity.fieldclasses.StartDTO;
import entity.fieldclasses.ShippingCompanyDTO;
import entity.fieldclasses.StreetDTO;
import entity.fieldclasses.TaxDTO;
public class MySQLFieldDAO implements FieldDAO {


	public int getFieldCount() throws RuntimeException{
		Connector c = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper m = new SQLMapper();
		String query = m.getStatement(5);
		try {
			ResultSet rs = c.doQuery(query);
			rs.next();
			int fieldCount = rs.getInt(1);
			return fieldCount;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
	}
	@Override
	public FieldDTO getField(int id) throws RuntimeException {Connector c = new Connector();
	int fieldnumber = 0;
	String fieldname = null;
	String type = null;
	int colorint;
	Color color = null;	

	/* Alt SQL er holdt ude af java koden */
	SQLMapper m = new SQLMapper();
	String query = m.getStatement(6);
	String newquery = query.replace("##", ""+id);
	try {
		ResultSet rs = c.doQuery(newquery);
		while(rs.next()){
			fieldnumber = rs.getInt(1);
			fieldname = rs.getString(2);
			type = rs.getString(3);
//			colorint = rs.getInt(4);
//			color = new Color(colorint);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Error");
	}

	String newnewquery = newquery.replace("`field`", "`"+type+"`");
	try {
		ResultSet rs = c.doQuery(newnewquery);
		while(rs.next()){
			switch (type) {
			case "street":
				int streetgroup = rs.getInt(2);
				int streetprice = rs.getInt(4);
				int streetrent = rs.getInt(5);
				colorint = rs.getInt(3);
				color = new Color(colorint);
				StreetDTO streetfield = new StreetDTO(fieldnumber, fieldname, color, streetprice, streetrent, streetgroup);
				return streetfield;
			case "jail":
				JailDTO jailfield = new JailDTO(fieldnumber, fieldname, color);
				return jailfield;
			case "chance":
				ChanceDTO chancefield = new ChanceDTO(fieldnumber, fieldname, color);
				return chancefield;
			case "brewery":
				int breweryprice = rs.getInt(2);
				int breweryrent = rs.getInt(3);
				BreweryDTO breweryfield = new BreweryDTO(fieldnumber, fieldname, color, breweryprice, breweryrent);
				return breweryfield;
			case "refuge":
				if (fieldnumber == 0) {
					int bonus = rs.getInt(2);
					StartDTO startfield = new StartDTO(fieldnumber, fieldname, color, bonus);
					return startfield; 
				} 
				else {
					RefugeDTO refugefield = new RefugeDTO(fieldnumber,fieldname,color);
					return refugefield;
				}
			case "shipping_company":
				int shippingprice = rs.getInt(2);
				int shippingrent_1 = rs.getInt(4);
				int shippingrent_2 = rs.getInt(5);
				int shippingrent_3 = rs.getInt(6);
				int shippingrent_4 = rs.getInt(7);

				ShippingCompanyDTO shippingfield = new ShippingCompanyDTO(fieldnumber, fieldname, color, shippingprice, shippingrent_1, shippingrent_2, shippingrent_3, shippingrent_4);
				return shippingfield;
			case "tax":
				int taxamount = rs.getInt(2);
				int taxrate = rs.getInt(3);
				TaxDTO taxfield = new TaxDTO(fieldnumber, fieldname, color, taxamount, taxrate);
				return taxfield;
			default:
				break;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Error");
	}

	return null;
	}



}






