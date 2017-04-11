package boundary.dao;
import entity.PlayerList;
public interface PlayerDAO {
	public PlayerList getPlayers() throws RuntimeException;
	public int getPlayerCount() throws RuntimeException;
}
