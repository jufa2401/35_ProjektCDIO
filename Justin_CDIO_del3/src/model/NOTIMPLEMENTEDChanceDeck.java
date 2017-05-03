/*
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class ChanceDeck.
 */
public class NOTIMPLEMENTEDChanceDeck {
	
	/** The cards. */
	ArrayList<NOTIMPLEMENTEDChanceCard> cards;
	
	/** The discards. */
	ArrayList<NOTIMPLEMENTEDChanceCard> discards;
	
	/** The language. */
	String language;

	/**
	 * Instantiates a new chance deck.
	 */
	public NOTIMPLEMENTEDChanceDeck() {
		cards = new ArrayList<>();
		discards = new ArrayList<>();

		shuffle();
	}

	/**
	 * Shuffle.
	 */
	public void shuffle() {
		// System.out.println("shuffling chance");

		final Random random = new Random();
		while (discards.size() > 0) {
			final int index = Math.abs(random.nextInt()) % discards.size();
			cards.add(discards.get(index));
			discards.remove(index);
		}

		// System.out.println("GOT Chance: "+card.label);

	}

}