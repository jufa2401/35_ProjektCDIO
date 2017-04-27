/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Die.
 */
public class Die {
	
	/** The sides. */
	private int FaceValue, sides;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return FaceValue;
	}

	/**
	 * Roll.
	 *
	 * @return the int
	 */
	public int roll() {
		this.sides = 6;
		return FaceValue = (int) (Math.random() * sides + 1);
	}

	/**
	 * Method setDice er lavet specifikt til test se TestJail.
	 *
	 * @param i the new value
	 */
	public void setValue(int i) {
		FaceValue = i;
	}
}
