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
		discards.add(new ChanceCard(0,language));
		discards.add(new ChanceCard(0,"pay poor tax"));
		discards.add(new ChanceCard(0,"elected chairman of the board"));
		discards.add(new ChanceCard(0,"bank pays"));
		discards.add(new ChanceCard(0,"collect 150"));
		discards.add(new ChanceCard(0,"make repairs"));
		discards.add(new ChanceCard(1,"GO TO JAIL"));
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
	}

	public int getChance(int location)
	{
		int rval = location;

		if(cards.size()==0) {
			shuffle();
		}
		ChanceCard card = (ChanceCard)cards.get(0);
		cards.remove(0);
		discards.add(card);
		if(card.type==1) {
			rval = 10;
		}
		else if(card.type==2) {
			loops++;
			rval = 0;
		}
		else if(card.type==3) {
			if(location>11) {
				loops++;
			}
			rval = 11;
		}
		else if(card.type==4) {
			rval = 39;
		}
		else if(card.type==5) {
			if(location==7) {
				rval = 12;
			}
			else if(location==22) {
				rval = 28;
			}
			else if(location==36) {
				loops++;
				rval = 12;
			}
			else {
				System.out.println("I fucked up");
			}
		}
		else if(card.type==6) {
			if(location>24) {
				loops++;
			}
			rval = 24;
		}
		else if(card.type==7) {
			loops++;
			rval = 5;
		}
		else if(card.type==8) {
			rval = location-3;
		}
		else if(card.type==9) {
			if(location==7) {
				rval = 15;
			}
			else if(location==22) {
				rval = 25;
			}
			else if(location==36) {
				loops++;
				rval = 5;
			}
		}

		//			System.out.println("GOT Chance: "+card.label);


		return(rval);
	}

}