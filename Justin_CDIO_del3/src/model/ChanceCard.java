/*
 * 
 */
package model;

// TODO: Auto-generated Javadoc
/**
 * The Class ChanceCard.
 */
public class ChanceCard {
	
	/** The cardid. */
	private int cardid;
	
	/** The type. */
	protected int type;
	
	/** The move relative. */
	private int move_relative;
	
	/** The move absolute. */
	private int move_absolute;
	
	/** The pay 1. */
	private int pay_1;
	
	/** The pay 2. */
	private int pay_2;
	
	/** The cardname. */
	private String cardname;

	/**
	 * Instantiates a new chance card.
	 *
	 * @param cardid the cardid
	 * @param type the type
	 * @param cardname the cardname
	 * @param move_relative the move relative
	 * @param move_absolute the move absolute
	 * @param pay_1 the pay 1
	 * @param pay_2 the pay 2
	 */
	public ChanceCard(int cardid, int type, String cardname, int move_relative, int move_absolute, int pay_1, int pay_2)

	{
		this.cardid = cardid;
		this.type = type;
		this.cardname = cardname;
		this.move_relative = move_relative;
		this.move_absolute = move_absolute;
		this.pay_1 = pay_1;
		this.pay_2 = pay_2;
	}
}
