	package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.StartDTO;

public class TestStart {
	private PlayerDTO player;
	private StartDTO start;
	GameBoardDTO gb = new GameBoardDTO();
	@Before
	public void setUp() throws Exception {
		
		player = new PlayerDTO(1, "spiller", 0, 1000, 0);
		start = new StartDTO(0, "start", null, 4000);
	}

	@After
	public void tearDown() throws Exception {
		player = new PlayerDTO(1, "spiller", 0, 1000, 0);
	}

	

	@Test
	public void testEntities() {
		Assert.assertNotNull(player);
			Assert.assertNotNull(start);			
			Assert.assertTrue(start instanceof StartDTO);
	}
	@Test
	public final void testMoveToFieldBonus4000() {
		int expected = 1000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester påvirkningen på spilleren, altså balancen.
		player.moveToField(35, gb);
		player.moveToField(5, gb);
		expected = 1000 + 4000;
		actual = player.getBalance();
		Assert.assertEquals(expected, actual);
	}

   	


}
