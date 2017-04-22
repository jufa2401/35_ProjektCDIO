package boundary.dao;

import entity.ChanceCard;

public interface ChanceDAO {
		public int getChanceCardCount() throws RuntimeException;
		public ChanceCard getChance() throws RuntimeException;

}
