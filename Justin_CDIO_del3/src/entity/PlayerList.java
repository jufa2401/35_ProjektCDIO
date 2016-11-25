package entity;

public class PlayerList {
	//Tomt Playerarray
	private int playerCount;
	private Player[] players;

// For hver spiller der kommer ind i spillet, tæller vi 1 op i players arrayet
	public PlayerList (int playerCount) {
		this.players = new Player[playerCount];
		for (int c = 0; c < playerCount; c++)
			players[c] = new Player();
		this.playerCount = playerCount;
	}

// Vi tildeler spillerne ID, og tilføjer spillerne med  parametrene navn og balance
	public void addPlayer(int ID, String name) {
		players[ID] = new Player(name, 30000);
	}
	
	public Player getPlayer(int ID) {
		
		return players[ID];
	}
	public int getNumberofPlayer() {
		return playerCount;
	}

/*
 *  Kunne ikke få GUI'en til at vise spillere med samme navn,
 *  og laver derfor kode som stopper brugeren for at gøre dette
*/
	public boolean isNameUsed(int numberOfDefinedPlayers, String name) {
		boolean isUsed = false;
		for (int i = 0; i < numberOfDefinedPlayers; i++) {
			if (players[i].getName().equalsIgnoreCase(name)){
				isUsed = true;	
			}
		}
		return isUsed;
	}

// Checker for vinder, ved at tælle tabere
	public boolean isWinner() {
		int n = 0;
		for (int c = 0; c < playerCount; c++) {
			if (!players[c].hasLost()) {
				n++;	
			} 
		}
		return (n == 1);
	}
	
	// Forudsætter at vi ved der er en vinder
	public String getWinner() {
		String name = "";
		for (int c = 0; c < playerCount; c++) {
			if (!players[c].hasLost()) {
				name = players[c].getName();	
			} 
		}
		return name;
	}


}

