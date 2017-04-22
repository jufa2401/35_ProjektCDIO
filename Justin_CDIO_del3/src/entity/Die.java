package entity;
public class Die {
	private int FaceValue, sides; 

	public int roll(){
		this.sides = 6;
		return FaceValue = (int)(Math.random() * sides + 1);
	}
	public int getValue(){
		return FaceValue;   
	}	
	/**
	 * Method setDice er lavet specifikt til test se TestJail
	 * @return
	 */
	public void setValue (int i ) {
		FaceValue = i;
	}
}
