/*
 * 
 */
package model.daoboundary;

import model.PlayerList;

/**
 * Interface til at hente spillerrelevant data fr.
 */
public interface PlayerDAO {
	
	/**
	 * Metode til at hente spillerantal fra databasen.
	 *
	 * @return the player count
	 * @throws RuntimeException the runtime exception
	 */
	public int getPlayerCount() throws RuntimeException;

	/**
	 * Gets the players.
	 *
	 * @return the players
	 * @throws RuntimeException the runtime exception
	 */
	public PlayerList getPlayers() throws RuntimeException;
}
