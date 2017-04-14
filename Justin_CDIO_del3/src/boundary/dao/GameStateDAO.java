package boundary.dao;

import entity.GameBoardDTO;
import entity.PlayerList;

public interface GameStateDAO {
	public void saveFieldStatus(GameBoardDTO gb) throws RuntimeException;
	public void emptyDataEntries()throws RuntimeException;
	public void savePlayerStatus(PlayerList p) throws RuntimeException; 
	public void loadFieldStatus(PlayerList pl, GameBoardDTO gb) throws RuntimeException;

}
