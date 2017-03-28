package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Die;

public class TestDie {

	Die d;

	@Before
	public void setUp() throws Exception {
		d = new Die();
	}

	@After
	public void tearDown() throws Exception {
		d = null;
	}

	@Test
	/*
	 * Tester om vores terning er pålidelig over 600000 kast med en usikkerhed på 4%.
	 * Altså da vi har 6 mulige udfalad og 600000 kast, kan det teoretisk forventes at hvert mulig udfald optræder mellem 960 og 1040 gange. 
	 */
	public final void testRoll() {

		int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;

		for (int i=1; i<600001; i++){
			int roll = d.roll();

			if (roll == 1){
				one ++;
			}
			else if (roll == 2){
				two ++;
			}
			else if (roll == 3){
				three ++;
			}
			else if (roll == 4){
				four ++;
			}
			else if (roll == 5){
				five ++;
			}
			else if (roll == 6){
				six ++;
			}
		}

		assertTrue("Fejl, for få 1'ere " + one, 96000 <= one);
		assertTrue("Fejl, for mange 1'ere " + one, 104000 >= one);

		assertTrue("Fejl, for få 2'ere " + two, 96000 <= two); 
		assertTrue("Fejl, for mange 2'ere " + two, 104000 >= two);

		assertTrue("Fejl, for få 3'ere " + three, 96000 <= three); 
		assertTrue("Fejl, for mange 3'ere " + three, 104000 >= three);

		assertTrue("Fejl, for få 4'ere " + four, 96000 <= four); 
		assertTrue("Fejl, for mange 4'ere " + four, 104000 >= four);

		assertTrue("Fejl, for få 5'ere " + five, 96000 <= five); 
		assertTrue("Fejl, for mange 5'ere " + five, 104000 >= five);

		assertTrue("Fejl, for få 6'ere " + six, 96000 <= six); 
		assertTrue("Fejl, for mange 6'ere " + six, 104000 >= six);

		
		System.out.println("\nForventet fordeling for nedenstående er mellem 96000 - 104000 \n");
		System.out.println("Antal 1'ere: " + one);
		System.out.println("Antal 2'ere: " + two);
		System.out.println("Antal 3'ere: " + three);
		System.out.println("Antal 4'ere: " + four);
		System.out.println("Antal 5'ere: " + five);
		System.out.println("Antal 6'ere: " + six);
		System.out.println("\n");



	}

	@Test
	/*
	 * Tester over 600 kast om der er muligt at se et mønster ved øjekast på de printede arrays.
	 * Der bliver lageret 60 values i et array, som derefter bliver printet. 
	 * Dette sker 10 gange, altså har vi 600 kast fordelt på 10 printede arrays. 
	 */
	public final void testGetValue() {

		for (int c=0; c<10; c++){
			int[] testArray = new int[60];
			for (int i=0; i<testArray.length; i++){
				d.roll();
				testArray[i] = d.getValue();
			}
			System.out.println(Arrays.toString(testArray));
		}
	}


}
