
/**
 * Implementation of a card type.
 * 
 * @author Alex Tah
 * @since July 13 2014
 */


public class Card {
	private Suit mySuit;      //one of the four valid suits
	private int myNumber;     //value of card. Ace:1, J-K: 11-13
	
	
	
	/**
	 * Card constructor
	 * @param aSuit      suit of the card
	 * @param aNumber    number of the card
	 */
	
	public Card(Suit aSuit, int aNumber) {
		this.mySuit = aSuit;
		
		if (aNumber  >= 1 && aNumber <= 13) {
		    this.myNumber = aNumber;
		} else {
			
			//modify this to throw exception
			System.err.println(aNumber + " is not a valid card number");
			System.exit(1);
		}
	}
	
	/**
	 * Return number of the card.
	 * @return the number
	 */
	public int getNumber() {
		return myNumber;
	}
	
	public String toString() {   //overload object class method, make it our own
		String numStr = "Error";
		
		switch(this.myNumber) {
		
		case 2:
			numStr = "Two";
			break;
		case 3:
			numStr = "Three";
			break;
		case 4:
			numStr = "Four";
			break;
		case 5:
			numStr = "Five";
			break;
		case 6:
			numStr = "Six";
			break;
		case 7:
			numStr = "Seven";
			break;
		case 8:
			numStr = "Eight";
			break;
		case 9:
			numStr = "Nine";
			break;
		case 10:
			numStr = "Ten";
			break;
		case 11:
			numStr = "Jack";
			break;
		case 12:
			numStr = "Queen";
			break;
		case 13:
			numStr = "King";
			break;
		case 1:
			numStr = "Ace";
			break;
		}
		
		return numStr + " of " + mySuit.toString();
	}

}
