package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.GameBoardDTO;
import entity.PlayerDTO;
import entity.fieldclasses.FieldDTO;
import entity.fieldclasses.TaxDTO;

public class TestTax{

	private PlayerDTO player;

	private GameBoardDTO gb = new GameBoardDTO();

	@Before
	public void setUp() throws Exception {
		player = new PlayerDTO(1, "Spiller", 1, 10000, 0);
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
		TaxDTO taxfield = null;
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 4) {
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

	@Test
	public void testLandOnField2() {
		int expected = 10000;
		int actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		TaxDTO taxfield = null;
		for(int index = 0; index < gb.getNumberOfFields(); index++) {
			FieldDTO field = gb.getField(index);
			if(field.getType() == 4) {
				taxfield = (TaxDTO) field;
 
			}
		}
		// Denne test af landonfield tjekker om spillerens balance bliver reduceret korrekt
		// når man sætter en skatte pct. for et felt
		taxfield.landOnField(player, 10);

		expected = 10000/10 * 9;
		actual = player.getBalance();


		Assert.assertEquals(expected, actual);
	}
}

