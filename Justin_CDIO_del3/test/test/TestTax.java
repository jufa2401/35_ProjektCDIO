package test;

import static org.junit.Assert.*; 

import java.awt.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import entity.fieldclasses.*;
import entity.*;

public class TestTax {

	private PlayerDTO player;
	private Tax taxfield;
	
	@Before
	public void setUp() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
		this.taxfield = new Tax(1, Color.black, 4000, 10);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
	}

//	@Test
//	public final void testGetRent() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testGetPrice() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testGetType() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public void testEntity() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.taxfield);
		
		Assert.assertTrue(this.player instanceof PlayerDTO);
		Assert.assertTrue(this.taxfield instanceof Tax);
	}
	
	@Test
	public final void testLandOnField() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester LandOnField hvor der vælges at betale 4000.
		taxfield.landOnField(this.player);
		
		expected = 10000 - 4000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
	
	@Test
	public final void testLandOnFieldOverload() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester LandOnField (Overload metoden) i forhold til tax når der vælges at betale 10% i stedet for 4000.
		taxfield.landOnField(this.player, this.taxfield.getTaxRate());
		
		expected = 10000 - (10000*10/100);
		actual = player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
}
