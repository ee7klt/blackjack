
import java.util.Random;

/**
 * Implementation of deck of cards
 * 
 * @author Alex Tah
 * @since July 13 2014
 * 
 * 
 *
 */

public class Deck {
	/**
	 * The array of cards in the deck, top card is first index.
	 */
	private Card[] myCards;
	
	/**
	 * number of cards currently in deck
	 */
	private int numCards;
	
	/**
	 * Constructor that defines number of decks (number of sets of 52 cards)
	 * and whether it should be shuffled
	 * @param numDeck   the number of individial decks in this deck
	 * @param shuffle   whether to shuffle the cards
	 */
	public Deck(int numDecks, boolean shuffle) {
		
		this.numCards = numDecks*52;
		this.myCards = new Card[this.numCards];
		
	}
	

}