package test;

import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Player;
import entity.fieldclasses.Refuge;

public class TestRefuge {
	private Player player;
	private Refuge refuge500;
	private Refuge refuge5000;
	private Refuge refugeNegative700;
	
	@Before
	public void setUp() throws Exception {
		this.player = new Player("Doland Dak", 1000);
		this.refuge500 = new Refuge(1, Color.red, 500);
		this.refuge5000 = new Refuge(2, Color.BLACK, 5000);
		this.refugeNegative700 = new Refuge(3,Color.GREEN, -700);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player("Doland Dak", 1000);
	}

	

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		
			Assert.assertNotNull(this.refuge500);
			Assert.assertNotNull(this.refuge5000);
			Assert.assertNotNull(this.refugeNegative700);
			
			Assert.assertTrue(this.refuge500 instanceof Refuge);
			Assert.assertTrue(this.refuge5000 instanceof Refuge);
			Assert.assertTrue(this.refugeNegative700 instanceof Refuge);
	}
	@Test
	public final void testLandOnField500() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester påvirkning af spiller, altså balancen.
		this.refuge500.landOnField(this.player);
		
		expected = 1000 + 500;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testLandOnField5000() {
		int expected = 1000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester påvirkningen på spilleren, altså balancen.
		this.refuge5000.landOnField(this.player);
		
		expected = 1000 + 5000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public final void testLandOnFieldNegative700() {
		int expected = 1000; 
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//tester om negativ balance fra landOnField metode fungerer med negative tal:
		this.refugeNegative700.landOnField(this.player);
		
		expected = 1000 - 700;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}
   	


}
