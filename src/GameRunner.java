import java.util.Scanner;

/**
 * 
 * Game will how throw error and quit if either there are no more cards in the deck
 * if if player loses all his/her chips.
 * TO DO: make it more friendly.
 * 
 * TO DO: Give option to continue playing or quit after each round.
 * 
 * 
 * @author macgeekalex
 *
 */
public class GameRunner {
	
	
	public static void main(String[] args) {
       
		//Deck myDeck = new Deck(1,true);
      // myDeck.printDeck(24);
       //myDeck.testShuffle(1);
		
		//init
		Scanner sc = new Scanner(System.in);
		Deck theDeck = new Deck(1, true);
		
		//init the player
		Player me = new Player("Alex",100);
		Player dealer = new Player("Dealer");
		
		
		
		
		
		int numChips = 1;
		int numCards = 1;
		
		
		while (numChips >= 1 && numCards > 0) {
			
			me.addCard(theDeck.dealNextCard());
			dealer.addCard(theDeck.dealNextCard());
			me.addCard(theDeck.dealNextCard());
			dealer.addCard(theDeck.dealNextCard());
			
		
			
			// flags for when each player is finished hitting
			boolean meDone = false;
			boolean dealerDone = false;
			String ans = null;       // hit or stand
			int bet = 0;           // number of chips to bet
			numChips = me.getChips();  //number of chips left
			numCards = theDeck.getNumCards(); //number of cards left in deck
			
			
			
		
			
		//print initial hands
		
        System.out.println("========================");
		System.out.println("Cards dealt\n");
		me.printHand(true);
		me.printHandSum();
		//me.printChips();
		System.out.println("\n");
		dealer.printHand(false);
		//dealer.printHandSum();
		
		System.out.println("\n");

		me.printChips();
		System.out.println();
		  System.out.print("How many chips would you like to bet? (minimum 1): ");
		  bet = sc.nextInt();
		  
		//ask for bet amount
		while (bet < 1 || bet > numChips) {
			
			
			if (bet < 1) {
				System.out.print("Please bet a minimum of 1 chip.\n");
				me.printChips();
			}
			if (bet > numChips) {
				System.out.printf("You have only %d chips\n", numChips);
			}
			
			
			System.out.println();
			  System.out.print("How many chips would you like to bet? (minimum 1): \n");
			  bet = sc.nextInt();
	
		}
		
		while (!meDone || !dealerDone) {
			
			// check to see if the player has any chips left
			
			
			// player's turn
			if (!meDone) {
				System.out.print("Hit or Stay? (Enter H or S): ");
				ans = sc.next();
				System.out.println();
			}
			
			//player hits
			if (ans.compareToIgnoreCase("H") == 0) {
				
				// add next card in deck. if player is busted, we are done.
				meDone = !me.addCard(theDeck.dealNextCard());
				me.printHand(true);
				me.printHandSum();
				System.out.println();
				
			} else  { // player stays
				meDone = true;
			} 
			
			// dealer's turn
			if (!dealerDone) {
				if (dealer.getHandSum() >= 17) {
					System.out.println("The dealer stays.\n"); 
					dealerDone = true;
					dealer.printHand(false);
					//dealer.printHandSum();
				} else {
					System.out.println("The Dealer hits\n");
					dealerDone = !dealer.addCard(theDeck.dealNextCard());
					dealer.printHand(false);
					//dealer.printHandSum();
				}
			}
			
			//System.out.printf("meDone = %b\n", meDone);
			//System.out.printf("dealerDone = %b\n", dealerDone);
			System.out.println();
		}
		
		
		
		// print final hands
		System.out.println("Final hands:");
		me.printHand(true);
		me.printHandSum();
		System.out.println();
		dealer.printHand(true);
		dealer.printHandSum();
		
		int mySum = me.getHandSum();
		int dealerSum = dealer.getHandSum();
		
		
		
		
		/**
		 * if my score is greater than the dealer's score and still 21 or less I win.
		 * if dealer busts but I'm 21 and under, I win.
		 * if we both bust, I lose
		 */
		
		// push. money neither lost nor won
		if (mySum == dealerSum && mySum <= 21 && dealerSum <= 21) { 
			System.out.println("Push tie. Nobody wins!");
		}
		
		
		// my score is higher than dealer's
		if (mySum > dealerSum && mySum <= 21) {
			System.out.println("You win!");
			me.incrementChips(bet);
			//TO DO: add a way to deal with blackjack because it pays winning of 1.5x
			
		} 
		
		// dealer's score is higher than mine
		if (dealerSum > mySum && dealerSum <= 21) {
			System.out.println("Dealer wins!");
			me.decrementChips(bet);
		}
		
		// I bust 
		if (mySum > 21) {
			System.out.println("Dealer wins!");
			me.decrementChips(bet);
		} else if (dealerSum >21) {  // dealer busts, but I don't
			System.out.println("You win!");
			me.incrementChips(bet);
		}
		
		numChips = me.getChips();  //number of chips left
		numCards = theDeck.getNumCards(); //number of cards left in deck
		
		me.printChips();
		System.out.printf("There are %d cards left in the deck.\n",numCards);
		System.out.println();
		me.emptyHand();
		dealer.emptyHand();
		}
		//close scanner
				sc.close();
	}
}
