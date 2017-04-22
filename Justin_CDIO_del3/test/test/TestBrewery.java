package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.BreweryDTO;
import entity.fieldclasses.FieldDTO;

public class TestBrewery {

	private PlayerDTO player;

	private PlayerDTO ejer;
	private DiceCup d12;
	private GameBoardDTO gb = new GameBoardDTO();

	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
		ejer = new PlayerDTO(0, "ejer", 1, 10000, 0);


		d12 = new DiceCup();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(player);
	}

	@Test
	public void testLandOnField() {
		BreweryDTO brewfield = null;
		//		Sætter første brewery til at have samme ejer
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 2) {
				brewfield = (BreweryDTO) field;
				brewfield.setOwner(ejer); 
				break;
			}
		}
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		//Tester om når der er én ejer af af Breweryen og en anden spiller lander derpå om balancen bliver påvirket korrekt.
		d12.rollDiceCup();
		player.SaveDiceRoll(d12);

		brewfield.landOnField(player, gb);


		expected = 10000 - (100 * d12.getDiceSum());
		actual = player.getBalance();

		Assert.assertTrue(brewfield.getOwner() == ejer);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		BreweryDTO brewfield = null;
		//		Sætter første brewery til at have samme ejer
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 2) {
				brewfield = (BreweryDTO) field;
				brewfield.setOwner(ejer); 
			}
		}

		d12.rollDiceCup();
		player.SaveDiceRoll(d12);


		brewfield.landOnField(player, gb);

		// Tester om balanen bliver påvirket korrekt
		expected = 10000 - (200 * d12.getDiceSum());
		actual = player.getBalance();


		Assert.assertEquals(expected, actual);
	}
}

