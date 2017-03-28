package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import entity.fieldclasses.*;
import entity.*;

public class TestShippingCompany {
	
	private PlayerDTO player;
	private ShippingCompanyDTO ShippingCompanyDTO;
	private PlayerDTO ejer = new PlayerDTO("ejer", 5000);
	
	@Before
	public void setUp() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
		this.ShippingCompanyDTO = new ShippingCompanyDTO(1, Color.black, 2000);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new PlayerDTO("Doland Dak", 10000);
		this.ShippingCompanyDTO = new ShippingCompanyDTO(1, Color.black, 2000);
		this.player.setShippingCompanysOwned(0);
	}
	
	@Test 
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.ShippingCompanyDTO);
		
		Assert.assertTrue(this.ShippingCompanyDTO instanceof ShippingCompanyDTO);
	}

//	@Test
//	public final void testGetRent() {
//		fail("Not yet implemented"); //TODO - Er ikke sikker på om denne metode er interresant at teste. 	
//	}
//
//	@Test
//	public final void testGetPrice() {
//		fail("Not yet implemented"); // TODO - Er testet i samtlige andre klasser. 
//	}
//
//	@Test
//	public final void testGetType() {
//		fail("Not yet implemented"); // TODO . Not interesting. 
//	}

	@Test
	public final void testLandOnField1() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		ejer.setShippingCompanysOwned(1);
		this.ShippingCompanyDTO.setOwner(ejer);
		this.ShippingCompanyDTO.landOnField(this.player);
		expected = 10000 - 500;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
	@Test
	public final void testLandOnFIeld2() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		ejer.setShippingCompanysOwned(2);
		this.ShippingCompanyDTO.setOwner(ejer);
		this.ShippingCompanyDTO.landOnField(this.player);
		expected = 10000 - 1000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
	@Test
	public final void testLandOnFIeld3() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		ejer.setShippingCompanysOwned(3);
		this.ShippingCompanyDTO.setOwner(ejer);
		this.ShippingCompanyDTO.landOnField(this.player);
		expected = 10000 - 2000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
	@Test
	public final void testLandOnFIeld4() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		ejer.setShippingCompanysOwned(4);
		this.ShippingCompanyDTO.setOwner(ejer);
		this.ShippingCompanyDTO.landOnField(this.player);
		expected = 10000 - 4000;
		actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
	}
	 
	
	@Test
	public final void testBuyField() {
		int expected = 10000;
		int actual = this.player.getBalance();
		Assert.assertEquals(expected, actual);
		
		// Tester om metoden buyField tilføjer en ShippingCompany til spilleren.
		this.ShippingCompanyDTO.buyField(this.player);
		
		Assert.assertTrue(this.player.getShippingCompanysOwned() == 1);
		}
	

//	@Test
//	public final void testShippingCompany() {
//		fail("Not yet implemented"); // TODO - NOT INTERESTING
//	}

}
