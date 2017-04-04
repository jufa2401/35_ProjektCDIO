package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.*;
import entity.fieldclasses.*;

public class TestStreet {
	
	private PlayerDTO player;
	private StreetDTO Street1000;
	private StreetDTO Street4000;
	private StreetDTO StreetNegative500;
	PlayerDTO ejer = new PlayerDTO("ejer", 5000);
	
	@Before
	public void setUp() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
		//Player ejer = new Player("ejer", 1000);
		this.Street1000 = new StreetDTO(1, Color.black, 2000, 1000);
		this.Street4000 = new StreetDTO(2, Color.blue, 6000, 4000);
		this.StreetNegative500 = new StreetDTO(3, Color.yellow, 100, -500);
		this.Street1000.setOwner(ejer);
		this.Street4000.setOwner(ejer);
		this.StreetNegative500.setOwner(ejer);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
	
	}

	@Test
	public void testEntity() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.Street1000);
		Assert.assertNotNull(this.Street4000);
		Assert.assertNotNull(this.StreetNegative500);
		
		Assert.assertTrue(this.Street1000 instanceof StreetDTO);
		Assert.assertTrue(this.Street4000 instanceof StreetDTO);
		Assert.assertTrue(this.StreetNegative500 instanceof StreetDTO);
	}
	
	@Test
	public final void testGetRent() {
		int expected = 1000;
		int actual = this.Street1000.getRent();
		Assert.assertEquals(expected, actual);
		
	}

	@Test
	public final void testGetPrice() {
		int expected = 2000;
		int actual = this.Street1000.getPrice();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testGetType() {
		int expected = 5;
		int actual = this.Street1000.getType();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void testLandOnField1000() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tester LandOnField metoden og om den påvirker balances som forventet.
		this.Street1000.landOnField(this.player);
		
		expected = 10000 - 1000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		}
	
	@Test
	public final void testLandOnField2000() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//Tesert udfaldet af LandOnField metoden i Street og hvordan balancen påvirkes.
		this.Street4000.landOnField(this.player);
		
		expected = 10000 - 4000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public final void testLandOnFieldNegative500() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//tester om hvordan rent metoden fungere sammen med player klassen.
		// tester om en spiller modtager penge når han skal betale et negativt beløb - Overholder klassen matematiske regler (-- = +).
		this.StreetNegative500.landOnField(this.player);
		
		expected = 10000 + 500;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		//tester om ejeren af feltet er blevet trukkt penge.
		expected = 5000 - 500;
		actual = ejer.getBalance();
		Assert.assertEquals(expected, actual);	
	}
	
}
