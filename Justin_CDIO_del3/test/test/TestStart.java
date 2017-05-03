/*
 * 
 */
package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.PlayerDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.StartDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TestStart.
 */
public class TestStart {
	
	/** The player. */
	private PlayerDTO player;
	
	/** The start. */
	private StartDTO start;
	
	/** The GB. */
	private final GameBoardDTO GB = new GameBoardDTO();

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {

		player = new PlayerDTO(1, "spiller", 0, 1000, 0);
		start = new StartDTO(0, "start", null, 4000);
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		player = new PlayerDTO(1, "spiller", 0, 1000, 0);
	}

	/**
	 * Test entities.
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(player);
		Assert.assertNotNull(start);
		Assert.assertTrue(start instanceof StartDTO);
	}

	/**
	 * Test move to field bonus 4000.
	 */
	@Test
	public final void testMoveToFieldBonus4000() {
		int expected = 1000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		// Tester påvirkningen på spilleren, altså balancen.
		player.moveToField(35, GB);
		player.moveToField(5, GB);
		expected = 1000 + 4000;
		actual = player.getBalance();
		Assert.assertEquals(expected, actual);
	}

}
