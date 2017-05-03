/*
 * 
 */
package model.daoimplementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Connector;
import model.PlayerDTO;
import model.PlayerList;
import model.daoboundary.GameStateDAO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.Ownable;
import model.fieldclasses.StreetDTO;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse gemmer og indlæser til/fra field_status og player_status
 * tabellerne.
 *
 * @author Justin
 */
public class MySQLGameStateDAO implements GameStateDAO {

	/**
	 * Checker om der er gemt noget i databassen.
	 *
	 * @return true, if successful
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public boolean checkFieldStatus() throws RuntimeException {
		Connector C = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper M = new SQLMapper();
		String query = M.getStatement(14);
		try {
			ResultSet rs = C.doQuery(query);
			rs.next();
			int fieldstatuscount = rs.getInt(1);

			if (fieldstatuscount > 0) {
				return true;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error, checking field status");
		}

		return false;

	}

	/**
	 * Tømmer tabellerne field_status og player_status for datainput.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public void emptyDataEntries() throws RuntimeException {
		Connector C = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper M = new SQLMapper();
		String query = M.getStatement(8);
		String query2 = M.getStatement(9);
		try {

			C.doUpdate(query2);
			C.doUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error emptying player/field status");
		}
	}

	/**
	 * Indlæser feltets status.
	 *
	 * @param pl the pl
	 * @param gb the gb
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public void loadFieldStatus(PlayerList pl, GameBoardDTO gb) throws RuntimeException {
		Connector C = new Connector();
		//		int count = 0; // count er blevet lavet af debugging hensyn
		/* Alt SQL er holdt ude af java koden */
		SQLMapper M = new SQLMapper();
		String query = M.getStatement(11);
		try {
			ResultSet rs = C.doQuery(query);
			while (rs.next()) {
				int fieldid = rs.getInt("field_id");
				int ownerid = rs.getInt("owned_by"); // check
				int houses = rs.getInt("street_houses");
				// int hotels = rs.getInt(4);
				FieldDTO field = gb.getField(fieldid);
				int type = field.getType();
				if (ownerid >= 0) {
					Ownable ofield = (Ownable) field;
					PlayerDTO p = pl.getPlayer(ownerid);
					ofield.setOwner(p);
					if (type == 5) {
						StreetDTO sfield = (StreetDTO) gb.getField(fieldid);
						sfield.setHouses(houses);
						// sfield.setHotels(hotels);
					}
				}
				//				++count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error, loading field_status ");
		}
	}

	/**
	 * Gemmer feltstatus i field_status tabellen.
	 *
	 * @param gb the gb
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public void saveFieldStatus(GameBoardDTO gb) throws RuntimeException {
		Connector C = new Connector();
		/* Alt SQL er holdt ude af java koden */
		SQLMapper M = new SQLMapper();
		String query = M.getStatement(7);

		try {
			for (int i = 0; i < gb.getNumberOfFields(); i++) {

				int ownerid = -1;
				// Hvis ownerid ændres til INTEGER kan den være NULL

				int houses = 0;
				int hotels = 0;
				FieldDTO field = gb.getField(i);
				int type = field.getType();
				switch (type) {
				case 1:
				case 2:
				case 5:
					Ownable ofield = (Ownable) field;
					PlayerDTO owner = ofield.getOwner();
					ownerid = (owner != null) ? owner.getPlayerID() : -1;
					if (type == 5) {
						StreetDTO sfield = (StreetDTO) gb.getField(i);
						houses = sfield.getHouses();
						hotels = sfield.getHotels();
					}
					break;
				default:
					break;
				}
				// String newquery = query +ownerid
				String newquery = query + i + "," + ownerid + "," + houses + "," + hotels + ");";

				// Gemmer kun felter som er ejet
				if (ownerid >= 0) {
					C.doUpdate(newquery);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error, saving field_status");
		}

	}
	@Override
	public void saveFieldStatusThread(GameBoardDTO gb){
		new Thread(
				new Runnable() {
					public void run() {
						try {
							saveFieldStatus(gb);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
	}

	/**
	 * Gemmer spillerens status i tabellen player_status.
	 *
	 * @param p the p
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public void savePlayerStatus(PlayerList p) throws RuntimeException {
		final Connector C = new Connector();
		/* Alt SQL er holdt ude af java koden */
		final SQLMapper M = new SQLMapper();
		int nplayers = p.getNumberofPlayer();
		String query = M.getStatement(10);

		try {

			for (int i = 0; i < nplayers; i++) {
				PlayerDTO player = p.getPlayer(i);
				String name = player.getName();
				int balance = player.getBalance();
				int roundsjail = player.getRoundsLeftJail();
				int position = player.getCurrentField();
				// Her kunne man have brugt Preparedstatement
				String newquery = query + i + ",'" + name + "'," + position + "," + balance + "," + roundsjail + ");";
				C.doUpdate(newquery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error, saving player status");
		}
	}
	@Override
	public void savePlayerStatusThread(PlayerList p){
		new Thread(
				new Runnable() {
					public void run() {
						try {
							savePlayerStatus(p);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
	}

}