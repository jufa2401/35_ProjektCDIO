package entity;

import java.util.ArrayList;
import java.util.Random;

public class ChanceDeck {
	ArrayList<ChanceCard> cards;
	ArrayList<ChanceCard> discards;
	String language;
	
	public ChanceDeck()
	{
		cards = new ArrayList<ChanceCard>();
		discards = new ArrayList<ChanceCard>();

//		Language for nogle kort er lavet i Dansk
		discards.add(new ChanceCard(0,0, language, 0, 0, 0, 0));
		discards.add(new ChanceCard(0,0, "pay poor tax", 0, 0, 0, 0));
		discards.add(new ChanceCard(0,0, "elected chairman of the board", 0, 0, 0, 0));
		discards.add(new ChanceCard(0,0, "bank pays", 0, 0, 0, 0));
		discards.add(new ChanceCard(0,0, "collect 150", 0, 0, 0, 0));
		discards.add(new ChanceCard(0,0, "make repairs", 0, 0, 0, 0));
		discards.add(new ChanceCard(1,0, "GO TO JAIL", 0, 0, 0, 0));
		discards.add(new ChanceCard(2,"advance to go"));
		discards.add(new ChanceCard(3,"advance to charles"));
		discards.add(new ChanceCard(4,"advance to boardwalk"));
		discards.add(new ChanceCard(5,"advance to nearest utility"));
		discards.add(new ChanceCard(6,"advance to illinois"));
		discards.add(new ChanceCard(7,"advance to reading"));
		discards.add(new ChanceCard(8,"go back 3 spaces"));
		discards.add(new ChanceCard(9,"advance to nearest RR"));
		discards.add(new ChanceCard(9,"advance to nearest RR"));

		shuffle();
	}

	public void shuffle()
	{
		//			System.out.println("shuffling chance");

		Random random = new Random();
		while(discards.size()>0) {
			int index = Math.abs(random.nextInt())%discards.size();
			cards.add(discards.get(index));
			discards.remove(index);
		}
	

	
		//			System.out.println("GOT Chance: "+card.label);


	}

}