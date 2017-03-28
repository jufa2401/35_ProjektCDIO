/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.DiceCup;

/**
 * @author Janus
 *
 */
public class TestDiceCup {

	DiceCup dice;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	dice = new DiceCup();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link data.DiceCup#getDiceSum()}.
	 */
	@Test
	public final void testGetDiceSum() {
int  two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0, ten = 0, eleven = 0, twelve = 0;
		
		

		for (int i=1; i<1100001; i++) {
			dice.rollDiceCup();
			int roll = dice.getDiceSum();
			
			if (roll == 2){
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
			else if (roll == 7){
				seven ++;	
			}
			else if (roll == 8){
				eight ++;
			}
			else if (roll == 9){
				nine ++;
			}
			else if (roll == 10){
				ten ++;
			}
			else if (roll == 11){
				eleven ++;
			}
			else if (roll == 12){
				twelve ++;
			}
			
		}
		
		
		assertTrue("Fejl, der er for mange 2'ere: " + two, 31803 >= two); 
		assertTrue("Fejl, der er for få 2'ere: " + two, 29356 <= two);

		assertTrue("Fejl, der er for mange 3'ere: " + three, 63606 >= three); 
		assertTrue("Fejl, der er for få 3'ere: " + three, 58713 <= three);

		assertTrue("Fejl, der er for mange 4'ere: " + four, 95295 >= four); 
		assertTrue("Fejl, der er for få 4'ere: " + four, 87964 <= four);

		assertTrue("Fejl, der er for mange 5'ere " + five, 127098 >= five); 
		assertTrue("Fejl, der er for få 5'ere: " + five, 117321 <= five);

		assertTrue("Fejl, der er for mange 6'ere: " + six, 158901 >= six); 
		assertTrue("Fejl, der er for få 6'ere: " + six, 146678 <= six);
		
		assertTrue("Fejl, der er for mange 7'ere: " + seven, 190704 >= seven); 
		assertTrue("Fejl, der er for få 7'ere: " + seven, 176035 <= seven);
		
		assertTrue("Fejl, der er for mange 8'ere: " + eight, 158901 >= eight); 
		assertTrue("Fejl, der for få 8'ere: " + eight, 146678 <= eight);
		
		assertTrue("Fejl, der er for mange 9'ere: " + nine, 127098 >= nine); 
		assertTrue("Fejl, der er for få 9'ere: " + nine, 117321 <= nine);
		
		assertTrue("Fejl, der er for mange 10'ere: " + ten, 95295 >= ten); 
		assertTrue("Fejl, der er for få 10'ere: " + ten, 87964 <= ten);
		
		assertTrue("Fejl, der er for mange 11'ere: " + eleven, 63606 >= eleven); 
		assertTrue("Fejl, der er for få 11'ere: " + eleven, 58713 <= eleven);
		
		assertTrue("Fejl, der er for mange 12'ere: " + twelve, 31803 >= twelve); 
		assertTrue("Fejl, der er for få 12'ere: " + twelve, 29356 <= twelve);
		
		System.out.println("Nedenstående fordeling er fordelingen af de mulige udfald: \n");
		System.out.println("Antal 2'ere: " + two);
		System.out.println("Antal 3'ere: " + three);
		System.out.println("Antal 4'ere: " + four);
		System.out.println("Antal 5'ere: " + five);
		System.out.println("Antal 6'ere: " + six);
		System.out.println("Antal 7'ere: " + seven);
		System.out.println("Antal 8'ere: " + eight);
		System.out.println("Antal 9'ere: " + nine);
		System.out.println("Antal 10'ere: " + ten);
		System.out.println("Antal 11'ere: " + eleven);
		System.out.println("Antal 12'ere: " + twelve);
		System.out.println("\n");
	}

}
