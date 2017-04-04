package boundary.dao;
import entity.GameBoardDTO;
public interface GameBoardDAO {
	public GameBoardDTO getGameboard() throws RuntimeException;

}
