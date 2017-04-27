/*
 * 
 */
package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.PlayerDTO;
import model.fieldclasses.FieldDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.StreetDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TestStreet.
 */
public class TestStreet {

	/** The player. */
	private PlayerDTO player;

	/** The ejer. */
	private PlayerDTO ejer;
	
	/** The gb. */
	private GameBoardDTO gb = new GameBoardDTO();

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
		ejer = new PlayerDTO(0, "ejer", 1, 10000, 0);

	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test entities.
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(player);

	}

	/**
	 * Test land on field.
	 */
	@Test
	// Tester det om man får fratrukket saldo den rette leje, hvis man lander på
	// det aller første felt
	public void testLandOnField() {
		StreetDTO streetfield = null;
		// Sætter første Street til at have samme ejer
		for (int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if (field.getType() == 5) {
				streetfield = (StreetDTO) field;
				streetfield.setOwner(ejer);
				break;
			}
		}
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		// Tester om når der er én ejer af af Streeten og en anden spiller
		// lander derpå om balancen bliver påvirket korrekt.

		streetfield.landOnField(player);

		expected = 10000 - 50;
		actual = player.getBalance();

		Assert.assertTrue(streetfield.getOwner() == ejer);
		Assert.assertEquals(expected, actual);
		
		int expected2 = 10000 + 50;
		int actual2 = ejer.getBalance();
		Assert.assertEquals(expected2,actual2);
	}

	/**
	 * Test land on field 2.
	 */
	@Test
	// Tester om man får fratrukket den rette leje hvis man lander på den sidste
	// street
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		StreetDTO streetfield = null;
		// Sætter alle Street til at have samme ejer
		for (int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if (field.getType() == 5) {
				streetfield = (StreetDTO) field;
				streetfield.setOwner(ejer);
			}
		}
		streetfield.landOnField(player);

		expected = 10000 - 1000;
		actual = player.getBalance();
		Assert.assertEquals(expected, actual);
		int expected2 = 10000 + 1000;
		int actual2 = ejer.getBalance();
		Assert.assertEquals(expected2,actual2);
		
		
	}
}
