import java.util.Scanner;


public class GameRunner {
	
	public static void main(String[] args) {
       
		//Deck myDeck = new Deck(1,true);
      // myDeck.printDeck(24);
       //myDeck.testShuffle(1);
		
		//init
		Scanner sc = new Scanner(System.in);
		Deck theDeck = new Deck(1, true);
		
		//init the player
		Player me = new Player("Alex");
		Player dealer = new Player("Dealer");
		
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		me.addCard(theDeck.dealNextCard());
		dealer.addCard(theDeck.dealNextCard());
		
		
		//print initial hands
		
		System.out.println("Cards dealt\n");
		me.printHand(true);
		dealer.printHand(false);
		
		System.out.println("\n");

		
		// flags for when each player is finished hitting
		boolean meDone = false;
		boolean dealerDone = false;
		String ans = null;
		
		while (!meDone || !dealerDone) {
			
			
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
				System.out.println();
				
			} else { // player stays
				meDone = true;
			}
			
			// dealer's turn
			if (!dealerDone) {
				if (dealer.getHandSum() > 17) {
					System.out.println("The dealer stays.\n"); 
					dealerDone = true;
				} else {
					System.out.println("The Dealer hits\n");
					dealerDone = !dealer.addCard(theDeck.dealNextCard());
					dealer.printHand(false);
				}
			}
			
			System.out.println();
		}
		
		//close scanner
		sc.close();
		
		// print final hands
		
		me.printHand(true);
		dealer.printHand(true);
		
		int mySum = me.getHandSum();
		int dealerSum = dealer.getHandSum();
		
		
		/**
		 * if my score is greater than the dealer's score and still 21 or less I win.
		 * if dealer busts but I'm 21 and under, I win.
		 * if we both bust, I lose
		 */
		
		if (mySum > dealerSum && mySum <= 21) {
			System.out.println("You win!");
		} else if (dealerSum > 21) {
			if (mySum > 21) {
			   System.out.println("You lose!");
			} else {
				System.out.println("You win!");
			} 
			
		} else {
			System.out.println("You lose!");
		}
		
	}
}
