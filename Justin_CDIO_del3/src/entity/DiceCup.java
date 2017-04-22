package entity;

public class DiceCup {

	//Creates 2 dice objects. 
	protected Die d1 = new Die();
	protected Die d2 = new Die();

	/**
	 * Method slår med 2 terninger
	 * @return
	 */
	public void rollDiceCup() {
		d1.roll();
		d2.roll();	
	}

	public int[] getDiceValue()	{
		int[] array = {d1.getValue(), d2.getValue()};
		return array;
	}	
	/**
	 * Method setDiceSum er lavet udelukkende for testårsager se TestJail
	 * @return
	 */
	public void setDiceValue (int d1, int d2) {
		this.d1.setValue(d1);
		this.d2.setValue(d2);		
	}

	/**
	 * Method getDiceSum returnerer summen af terningerne
	 * @return
	 */
	public int getDiceSum()	{
		return d1.getValue() + d2.getValue();
	}	
}
