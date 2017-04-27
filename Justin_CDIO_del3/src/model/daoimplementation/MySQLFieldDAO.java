/*
 * 
 */
package model.daoimplementation;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.daoboundary.FieldDAO;
import model.fieldclasses.BreweryDTO;
import model.fieldclasses.ChanceDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.JailDTO;
import model.fieldclasses.RefugeDTO;
import model.fieldclasses.ShippingCompanyDTO;
import model.fieldclasses.StartDTO;
import model.fieldclasses.StreetDTO;
import model.fieldclasses.TaxDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQLFieldDAO.
 */
public class MySQLFieldDAO implements FieldDAO {

	/* (non-Javadoc)
	 * @see boundary.dao.FieldDAO#getField(int)
	 */
	@Override
	public FieldDTO getField(int id) throws RuntimeException {
		Connector c = new Connector();
		int fieldnumber = 0;
		String fieldname = null;
		String type = null;
		int colorint;
		Color color = null;


		SQLMapper m = new SQLMapper();
		String query = m.getStatement(6);
		String newquery = query.replace("##", "" + id);
		try {
			ResultSet rs = c.doQuery(newquery);
			while (rs.next()) {
				fieldnumber = rs.getInt("field_id");
				fieldname = rs.getString("field_name");
				type = rs.getString("type");
				// colorint = rs.getInt(4);
				// color = new Color(colorint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}

		String newnewquery = newquery.replace("`field`", "`" + type + "`");
		try {
			ResultSet rs = c.doQuery(newnewquery);
			while (rs.next()) {
				switch (type) {
				case "street":
					String streetgroup = rs.getString("group");
					colorint = rs.getInt("dec_color");
					int streetprice = rs.getInt("price");
					int streetrent = rs.getInt("standard_rent");
					int srent_1 = rs.getInt("rent_1");
					int srent_2 = rs.getInt("rent_2");
					int srent_3 = rs.getInt("rent_3");
					int srent_4 = rs.getInt("rent_4");
					int houseprice = rs.getInt("house_price");
					color = new Color(colorint);
					StreetDTO streetfield = new StreetDTO(fieldnumber, fieldname, streetgroup, color, streetprice,
							streetrent, srent_1, srent_2, srent_3, srent_4, houseprice);
					return streetfield;
				case "jail":
					JailDTO jailfield = new JailDTO(fieldnumber, fieldname, color);
					return jailfield;
				case "chance":
					ChanceDTO chancefield = new ChanceDTO(fieldnumber, fieldname, color);
					return chancefield;
				case "brewery":
					int breweryprice = rs.getInt("price");
					int rent_1 = rs.getInt("rent_1");
					int rent_2 = rs.getInt("rent_2");
					BreweryDTO breweryfield = new BreweryDTO(fieldnumber, fieldname, color, breweryprice, rent_1,
							rent_2);
					return breweryfield;
				case "refuge":
					if (fieldnumber == 0) {
						int bonus = rs.getInt("bonus");
						StartDTO startfield = new StartDTO(fieldnumber, fieldname, color, bonus);
						return startfield;
					} else {
						RefugeDTO refugefield = new RefugeDTO(fieldnumber, fieldname, color);
						return refugefield;
					}
				case "shipping_company":
					int shippingprice = rs.getInt("price");
					int shippingrent_1 = rs.getInt("rent_1");
					int shippingrent_2 = rs.getInt("rent_2");
					int shippingrent_3 = rs.getInt("rent_3");
					int shippingrent_4 = rs.getInt("rent_4");

					ShippingCompanyDTO shippingfield = new ShippingCompanyDTO(fieldnumber, fieldname, color,
							shippingprice, shippingrent_1, shippingrent_2, shippingrent_3, shippingrent_4);
					return shippingfield;
				case "tax":
					int taxamount = rs.getInt("tax_amount");
					int taxrate = rs.getInt("tax_rate");
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

	/* (non-Javadoc)
	 * @see boundary.dao.FieldDAO#getFieldCount()
	 */
	@Override
	public int getFieldCount() throws RuntimeException {
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

}
