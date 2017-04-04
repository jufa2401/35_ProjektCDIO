package daoboundary;
import java.util.ArrayList;

import entity.PlayerDTO;
public interface PlayerDAO {
	public ArrayList<PlayerDTO> getPlayer() throws RuntimeException;
}
