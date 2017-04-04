package daoboundary;
import entity.GameBoardDTO;
public interface GameBoardDAO {
	public GameBoardDTO getGameboard() throws RuntimeException;

}
