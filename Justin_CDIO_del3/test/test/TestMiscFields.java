	package test;

import java.awt.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.PlayerDTO;
import entity.fieldclasses.RefugeDTO;

public class TestMiscFields {
	private PlayerDTO player;
	private RefugeDTO refuge500;
	private RefugeDTO refuge5000;
	private RefugeDTO refugeNegative700;
	
	@Before
	public void setUp() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 1000);
		this.refuge500 = new RefugeDTO(1, Color.red, 500);
		this.refuge5000 = new RefugeDTO(2, Color.BLACK, 5000);
		this.refugeNegative700 = new RefugeDTO(3,Color.GREEN, -700);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 1000);
	}

	

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		
			Assert.assertNotNull(this.refuge500);
			Assert.assertNotNull(this.refuge5000);
			Assert.assertNotNull(this.refugeNegative700);
			
			Assert.assertTrue(this.refuge500 instanceof RefugeDTO);
			Assert.assertTrue(this.refuge5000 instanceof RefugeDTO);
			Assert.assertTrue(this.refugeNegative700 instanceof RefugeDTO);
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
