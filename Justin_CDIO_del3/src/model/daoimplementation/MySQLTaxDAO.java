package model.daoimplementation;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.daoboundary.TaxDAO;
import model.fieldclasses.TaxDTO;
public class MySQLTaxDAO implements TaxDAO {	
	@Override
	public TaxDTO getTax(int id, String name) throws RuntimeException {

	final Connector C = new Connector();
	PreparedStatement preparedstatement = null;
	
	int tax_amount = 0;
	int tax_rate = 0;
	int colorint = 0;
	final SQLMapper M = new SQLMapper();
	String query = M.getStatement(16);

	try {
		preparedstatement = C.prep(query);
		preparedstatement.setInt(1, id);
		ResultSet rs = preparedstatement.executeQuery();
		while (rs.next()) {
		tax_amount = rs.getInt("tax_amount");
		tax_rate = rs.getInt("tax_rate");
		}
	} catch (final SQLException e) {
		e.printStackTrace();
	}

	Color color = new Color(colorint);
	TaxDTO Taxfield = new TaxDTO(id, name, color, tax_amount, tax_rate);
	return Taxfield;
}
}