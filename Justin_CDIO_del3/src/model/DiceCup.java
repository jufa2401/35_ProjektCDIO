/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * Klasse som beskriver raflebægrets metoder.
 *
 * @author Justin
 */
public class DiceCup {

	/** The d 1. */
	// Creates 2 dice objects.
	protected Die d1 = new Die();
	
	/** The d 2. */
	protected Die d2 = new Die();

	/**
	 * Method getDiceSum returnerer summen af terningerne.
	 *
	 * @return the dice sum
	 */
	public int getDiceSum() {
		return d1.getValue() + d2.getValue();
	}

	/**
	 * Gets the dice value.
	 *
	 * @return the dice value
	 */
	public int[] getDiceValue() {
		int[] array = { d1.getValue(), d2.getValue() };
		return array;
	}

	/**
	 * Method slår med 2 terninger.
	 */
	public void rollDiceCup() {
		d1.roll();
		d2.roll();
	}

	/**
	 * Method setDiceSum er lavet udelukkende for testårsager se TestJail.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 */
	public void setDiceValue(int d1, int d2) {
		this.d1.setValue(d1);
		this.d2.setValue(d2);
	}
}
