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
import model.fieldclasses.TaxDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TestTax.
 */
public class TestTax {

	/** The player. */
	private PlayerDTO player;

	/** The GB. */
	private final GameBoardDTO GB = new GameBoardDTO();

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
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
	public void testLandOnField() {
		TaxDTO taxfield = null;
		for (int index = 0; index < GB.getNumberOfFields(); index++) {
			FieldDTO field = GB.getField(index);
			if (field.getType() == 4) {
				taxfield = (TaxDTO) field;
				break;
			}
		}
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		// Denne test af land on field bruger det første tax-felt i gameboardet
		// og tester om dens faste rate bliver trukket fra spilleren

		taxfield.landOnField(player);

		expected = 10000 - 4000;
		actual = player.getBalance();

		Assert.assertEquals(expected, actual);
	}

	/**
	 * Test land on field 2.
	 */
	@Test
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		TaxDTO taxfield = null;
		for (int index = 0; index < GB.getNumberOfFields(); index++) {
			FieldDTO field = GB.getField(index);
			if (field.getType() == 4) {
				taxfield = (TaxDTO) field;

			}
		}
		// Denne test af landonfield tjekker om spillerens balance bliver
		// reduceret korrekt
		// når man sætter en skatte pct. for et felt
		taxfield.landOnField(player, 10);

		expected = 10000 / 10 * 9;
		actual = player.getBalance();

		Assert.assertEquals(expected, actual);
	}
}
