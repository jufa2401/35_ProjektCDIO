/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * Denne klasse samler spille entiteterne i en Spillerliste med relevante
 * gennemløbsmetoder mm.
 * 
 * @author Justin
 *
 */
public class PlayerList {
	
	/** The player count. */
	// Tomt Playerarray
	private final int playerCount;
	
	/** The players. */
	private final PlayerDTO[] players;

	/**
	 * For hver spiller der kommer ind i spillet, tæller vi 1 op i players
	 * arrayet *DATABASE PROGRAMMERING*.
	 *
	 * @param playerCount the player count
	 */
	public PlayerList(int playerCount) {
		players = new PlayerDTO[playerCount];
		for (int c = 0; c < playerCount; c++)
			players[c] = new PlayerDTO();
		this.playerCount = playerCount;
	}

	/**
	 * Vi tildeler spillerne ID, og tilføjer spillerne med parametrene navn og
	 * balance *DATABASE PROGRAMMERING*.
	 *
	 * @param ID the id
	 * @param name the name
	 * @param pos the pos
	 * @param bal the bal
	 * @param rounds_left_jail the rounds left jail
	 */
	public void addPlayer(int ID, String name, int pos, int bal, int rounds_left_jail) {
		players[ID] = new PlayerDTO(ID, name, pos, bal, rounds_left_jail);

	}

	/**
	 * Gets the numberof player.
	 *
	 * @return the numberof player
	 */
	public int getNumberofPlayer() {
		return playerCount;
	}

	/**
	 * Gets the player.
	 *
	 * @param ID the id
	 * @return the player
	 */
	public PlayerDTO getPlayer(int ID) {
		return players[ID];
	}

	/**
	 * Forudsætter at vi ved der er en vinder.
	 *
	 * @return navnet på vinderen, for at det kan vises i GUI
	 */
	public String getWinner() {
		String name = "";
		for (int c = 0; c < playerCount; c++) {
			if (!players[c].hasLost()) {
				name = players[c].getName();
			}
		}
		return name;
	}

	/**
	 * Kunne ikke få GUI'en til at vise spillere med samme navn, og laver derfor
	 * kode som stopper brugeren for at gøre dette.
	 *
	 * @param numberOfDefinedPlayers the number of defined players
	 * @param name the name
	 * @return true, if is name used
	 */
	public boolean isNameUsed(int numberOfDefinedPlayers, String name) {
		boolean isUsed = false;
		for (int i = 0; i < numberOfDefinedPlayers; i++) {
			if (players[i].getName().equalsIgnoreCase(name)) {
				isUsed = true;
			}
		}
		return isUsed;
	}

	/**
	 * Checker for vinder, ved at tælle tabere.
	 *
	 * @return true, if is winner
	 */
	public boolean isWinner() {
		int n = 0;
		for (int c = 0; c < playerCount; c++) {
			if (!players[c].hasLost()) {
				n++;
			}
		}
		return (n == 1);
	}

}
