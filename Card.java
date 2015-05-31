public class Card {	

		public final static int SPADES = 0;
		public final static int HEARTS = 1;
		public final static int DIAMONDS = 2;
		public final static int CLUBS = 3;

		public final static int ACE = 1;
		public final static int KING = 13;
		public final static int QUEEN = 12;
		public final static int JACK = 11;
		public final static int TEN = 10;
		public final static int NINE = 9;
		public final static int EIGHT = 8;
		public final static int SEVEN = 7;
		public final static int SIX = 6;
		public final static int FIVE = 5;
		public final static int FOUR = 4;
		public final static int THREE = 3;
		public final static int TWO = 2;

		private final int suit;
		private final int value;

		/*write method for getValue and getSuit*/

		public Card(int theValue, int theSuit) {
			value = theValue;
			suit = theSuit;
		}

		public int getValue() {
			return value;
		}

		public int getSuit() {
			return suit;
		}

		public String getValueAsString() {
			switch(value) {
			case 1: return "Ace";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "Jack";
			case 12: return "Queen";
			default: return "King";
			}
		}
		
		public String getSuitAsString() { 
			switch (suit) {
			case SPADES: return "Spades";
			case HEARTS: return "Hearts";
			case DIAMONDS: return "Diamonds";
			case CLUBS: return "Clubs";
			default: return "Joker";
			}
		}

		public String toString() {
			return getValueAsString() + " of " + getSuitAsString();
		}
		
		public void aceCase(boolean isEleven){
			if(isEleven){
				
			}
			}
		}

