package entity;

public class ChanceCard {
	private int cardid;
	protected int type;
	private int move_relative;
	private int move_absolute;
	private int pay_1;
	private int pay_2;
	private String cardname;

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


