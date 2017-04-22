package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.DiceCup;
import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.JailDTO;

public class TestJail{

	private PlayerDTO player;
	private DiceCup d = new DiceCup();
	private GameBoardDTO gb = new GameBoardDTO();

	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
	}

	@After
	public void tearDown() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);

	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(player);

	}

	@Test
	public void testLandOnField() {
		JailDTO Jailfield = null;
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 6) {
				Jailfield = (JailDTO) field;
	
			}
		}
		int expectedfield = 1;
		int actualfield = player.getCurrentField();
		Assert.assertEquals(expectedfield, actualfield);

		// Denne test af land on field bruger det sidste Jail-felt i gameboardet
		// det forventes at spilleren smides tilbage på felt 10 efter at lande på jail2

		Jailfield.landOnField(player, gb);
		

		expectedfield = 10;
		actualfield = player.getCurrentField();


		Assert.assertEquals(expectedfield, actualfield);
	}
	/*
	@Test
	public void test3rolls() {
		JailDTO Jailfield = null;
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 6) {
				Jailfield = (JailDTO) field;
	
			}
		}
		int expectedfield = 1;
		int actualfield = player.getCurrentField();
		Assert.assertEquals(expectedfield, actualfield);

		// Denne test af land on field bruger det sidste Jail-felt i gameboardet
		// det forventes at spilleren smides tilbage på felt 10 efter at lande på jail2

		Jailfield.landOnField(player, gb);
		int jailposition = player.getCurrentField();
		
		d.rollDiceCup();
		d.rollDiceCup();
		d.rollDiceCup();
		
		
		expectedfield = jailposition + this.d.getDiceSum();
		actualfield = player.getCurrentField();
		Assert.assertEquals(expectedfield, actualfield);
	}
	@Test
	public void test2identical() {
		JailDTO Jailfield = null;
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 6) {
				Jailfield = (JailDTO) field;
	
			}
		}
		int expectedfield = 1;
		int actualfield = player.getCurrentField();
		Assert.assertEquals(expectedfield, actualfield);

		// Denne test af land on field bruger det sidste Jail-felt i gameboardet
		// det forventes at spilleren smides tilbage på felt 10 efter at lande på jail2

		Jailfield.landOnField(player, gb);
		int expectedRounds = 3;
		int actualRounds = player.getRoundsLeftJail();
		Assert.assertEquals(expectedRounds, actualRounds);
		
		this.d.setDiceValue(1, 1);
		
		expectedRounds = 0;
		actualfield = player.getRoundsLeftJail();
		Assert.assertEquals(expectedRounds, actualRounds);
	}
*/
}

