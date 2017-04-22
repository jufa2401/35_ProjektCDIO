package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.StreetDTO;

public class TestStreet{

	private PlayerDTO player;

	private PlayerDTO ejer;
	private GameBoardDTO gb = new GameBoardDTO();

	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
		ejer = new PlayerDTO(0, "ejer", 1, 10000, 0);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(player);

	}

	@Test
	// Tester det om man får fratrukket saldo den rette leje, hvis man lander på det aller første felt
	public void testLandOnField() {
		StreetDTO streetfield = null;
		//		Sætter første Street til at have samme ejer
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 5) {
				streetfield = (StreetDTO) field;
				streetfield.setOwner(ejer); 
				break;
			}
		}
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		//Tester om når der er én ejer af af Streeten og en anden spiller lander derpå om balancen bliver påvirket korrekt.

		streetfield.landOnField(player, gb);


		expected = 10000 - 50;
		actual = player.getBalance();

		Assert.assertTrue(streetfield.getOwner() == ejer);
		Assert.assertEquals(expected, actual);
	}

	@Test
	// Tester om man får fratrukket den rette leje hvis man lander på den sidste street
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		StreetDTO streetfield = null;
		//		Sætter alle Street til at have samme ejer
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 5) {
				streetfield = (StreetDTO) field;
				streetfield.setOwner(ejer); 
			}
		}
		streetfield.landOnField(player, gb);

		expected = 10000 - 1000;
		actual = player.getBalance();


		Assert.assertEquals(expected, actual);
	}
}

