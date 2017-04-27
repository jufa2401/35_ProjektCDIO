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
import model.fieldclasses.ShippingCompanyDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TestShippingCompany.
 */
public class TestShippingCompany {

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
	public void testLandOnField() {
		ShippingCompanyDTO shipfield = null;
		// Sætter første ShippingCompany til at have samme ejer
		for (int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if (field.getType() == 1) {
				shipfield = (ShippingCompanyDTO) field;
				shipfield.setOwner(ejer);
				break;
			}
		}
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		// Tester om når der er én ejer af af ShippingCompanyen og en anden
		// spiller lander derpå om balancen bliver påvirket korrekt.

		shipfield.landOnField(player, gb);

		expected = 10000 - 500;
		actual = player.getBalance();

		Assert.assertTrue(shipfield.getOwner() == ejer);
		Assert.assertEquals(expected, actual);
		
		int expected2 = 10000 + 500;
		int actual2 = ejer.getBalance();
		Assert.assertEquals(expected2,actual2);
	}

	/**
	 * Test land on field 2.
	 */
	@Test
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		ShippingCompanyDTO shipfield = null;
		// Sætter alle ShippingCompany til at have samme ejer
		for (int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if (field.getType() == 1) {
				shipfield = (ShippingCompanyDTO) field;
				shipfield.setOwner(ejer);
			}
		}
		shipfield.landOnField(player, gb);

		expected = 10000 - 4000;
		actual = player.getBalance();

		Assert.assertEquals(expected, actual);
		
		int expected2 = 10000 + 4000;
		int actual2 = ejer.getBalance();
		Assert.assertEquals(expected2,actual2);
	}
}
