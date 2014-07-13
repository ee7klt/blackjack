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
		me.printHandSum();
		System.out.println("\n");
		dealer.printHand(false);
		//dealer.printHandSum();
		
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
				me.printHandSum();
				System.out.println();
				
			} else { // player stays
				meDone = true;
			}
			
			// dealer's turn
			if (!dealerDone) {
				if (dealer.getHandSum() > 17) {
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
			
			System.out.println();
		}
		
		//close scanner
		sc.close();
		
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
			if (mySum == 21) { // winning blackjack pays 1.5x
				
			}
		} 
		
		// dealer's score is higher than mine
		if (dealerSum > mySum && dealerSum <= 21) {
			System.out.println("Dealer wins!");
		}
		
		// I bust 
		if (mySum > 21) {
			System.out.println("Dealer wins!");
		} else if (dealerSum >21) {  // dealer busts, but I don't
			System.out.println("You win!");
		}
		
	}
}
