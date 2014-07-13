
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
	 * Constructor with a default of one deck and no shuffling.
	 */
	public Deck() {
		
		// call the other constructor, defining one deck without shuffling
		this(1, false);
	}
	
	/**
	 * Constructor that defines number of decks (number of sets of 52 cards)
	 * and whether it should be shuffled
	 * @param numDeck   the number of individial decks in this deck
	 * @param shuffle   whether to shuffle the cards
	 */
	public Deck(int numDecks, boolean shuffle) {
		
		this.numCards = numDecks*52;
		
		this.myCards = new Card[this.numCards];
		
		//init card index
		int c = 0;
		
		//for each deck
		for (int d = 0; d <numDecks; d++) {
			
			//for each suit
			for (int s = 0; s < 4; s++) {
				
				//for each number
				for (int n = 1; n <= 13; n++) {
					
					//add a new card to the deck
					this.myCards[c] = new Card(Suit.values()[s],n);
					c++;
				}
			}
		}
		//shuffle if necessary
		if (shuffle) {
			this.shuffle();
		}
		
	}
	
	/**
	 * Shuffle deck by Knuth shuffle.
	 */
	public void shuffle() {
		//init random number generator
		Random rng = new Random();
		
		//temporary card
		Card temp;
		
		int j;
		for (int i = 0; i < this.numCards; i++) {
			
			//get a random card j between 0 and i to swap i's value with
			j = rng.nextInt(i);
			
			//do swap
			temp = this.myCards[i];
			this.myCards[i] = this.myCards[j];
			this.myCards[j] = temp;
		}
		
		
	}

}
