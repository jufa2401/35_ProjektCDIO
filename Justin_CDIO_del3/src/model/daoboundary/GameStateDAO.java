package model.daoboundary;

import model.PlayerList;
import model.fieldclasses.GameBoardDTO;

// TODO: Auto-generated Javadoc
/**
 * Dette interface styrer implementationen af gemmer og indlæser til/fra
 * field_status og player_status tabellerne.
 *
 * @author Justin
 */
public interface GameStateDAO {
	
	/**
	 * Laver et tjek på om der er noget gemt i databasen.
	 *
	 * @return true, if successful
	 * @throws RuntimeException the runtime exception
	 */
	public boolean checkFieldStatus() throws RuntimeException;

	/**
	 * Tømmer tabellerne field_status og player_status for datainput.
	 *
	 * @throws RuntimeException the runtime exception
	 */
	public void emptyDataEntries() throws RuntimeException;

	/**
	 * Indlæser feltets status.
	 *
	 * @param pl the pl
	 * @param gb the gb
	 * @throws RuntimeException the runtime exception
	 */
	public void loadFieldStatus(PlayerList pl, GameBoardDTO gb) throws RuntimeException;

	/**
	 * Gemmer feltstatus i field_status tabellen.
	 *
	 * @param gb the gb
	 * @throws RuntimeException the runtime exception
	 */
	public void saveFieldStatus(GameBoardDTO gb) throws RuntimeException;

	/**
	 * Gemmer spillerens status i tabellen player_status.
	 *
	 * @param p the p
	 * @throws RuntimeException the runtime exception
	 */
	public void savePlayerStatus(PlayerList p) throws RuntimeException;

}
