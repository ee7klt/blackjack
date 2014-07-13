/**
 * Implementation of blackjack player
 * @author Alex Tah
 *
 */


public class Player {
	/**
	 * player's name
	 */
	private String name;
	private int chips;

	
	
	/**
	 * Cards in player's current hand;
	 */
	private Card[] hand = new Card[10];
	
	/**
	 * number of cards in player's hand
	 */
	private int numCards;
	
	
	/**
	 * Dealer constructor (overloads player constructor)
	 * @param aName name of player
	 */
	public Player(String aName) {
		this(aName,0);
		
	}
	
	
	/**
	 * Player constructor
	 * @param aName name of player
	 */
	public Player(String aName, int numChips) {
		this.name = aName;
		this.chips = numChips;
		
		
		//set a player's hand to empty
		this.emptyHand();
		
	}
	
	public String getName() {
		return name;
	}
	
	
	/**
	 * add winnings to player's chips 
	 * @param numChips number of chips to add
	 */
	public void incrementChips(int numChips) {
		this.chips += numChips;
	}
	
	/**
	 * remove losings from player's chips
	 * @param numChips number of chips to remove
	 */
	public void decrementChips(int numChips) {
		this.chips -= numChips;
		
		if (this.chips <= 0) {
		   System.err.printf("%s is bankrupt. Cannot play anymore.\n", this.name );
		   System.exit(1);
		}
	}
	
	/**
	 * print number of chips left for player
	 * 
	 */
	public void printChips() {
		System.out.printf("%s has %d chips available\n",this.name,this.chips);
	}
	
	public int getChips() {
		return chips;
	}
	
	
	
	/**
	 * Reset player's hand to have no cards
	 */
	public void emptyHand() {
		for (int c = 0; c < 10; c++) {
			this.hand[c] = null;
		}
		this.numCards = 0;
	}

	/**
	 * Add card to player's hand
	 * 
	 * @param aCard card to add
	 * @return whether the sum of new hand is <= 21 (Bust?)
	 */
	public boolean addCard(Card aCard) {
		
		//print error if we already have max number of cards
		if (this.numCards == 10) {
			System.err.printf("%s's hand already has max number of cards; "+" cannot add another\n", this.name);
			System.exit(1);
		}
		
		//add new card in next slot and increment number of cards counter
		this.hand[this.numCards] = aCard;
		this.numCards++;
		
		return (this.getHandSum() <= 21);
	}
	
	
	/**
	 * Get the sum of player's hand
	 * @return the sum
	 */
	public int getHandSum() {
		
		int handSum = 0;
		int cardNum;
		int numAces = 0;
		
		//calc each card's contribution to the hand sum
		
		for (int c = 0; c < this.numCards; c++) {
			cardNum = this.hand[c].getNumber();
			if (cardNum == 1) {// Ace
				++numAces;
				handSum += 11;
			} else if (cardNum > 10) {  //Face
				handSum +=10;
			} else { //Number
			   handSum += cardNum;
			   
			}
			
			
			 // Convert value of an Ace to 1 while busting
			 
			while ((handSum > 21) && (numAces > 0)) {  
				handSum -= 10;
				numAces--;
			}
		}
		return handSum;
	}
	
	/**
	 * Print cards in the player's hand
	 * @param showFirstCard whether first card is hidden or not
	 */
	public void printHand(boolean showFirstCard) {
		System.out.printf("%s's cards:\n", this.name); 
				for (int c = 0; c < this.numCards; c++) {
					if (c == 0 && !showFirstCard) {
						System.out.println("  [hidden]");
					} else {
						System.out.printf("   %s\n", this.hand[c].toString());
					}
				}
	}
	
	
	/**
	 * Print total of player's hand
	 */
	public void printHandSum() {
		int total = this.getHandSum();
		System.out.printf("%s's total is currently: %d\n",this.name, total);
		
	}
	
	// TO DO: Print sum of hand. DONE
}
