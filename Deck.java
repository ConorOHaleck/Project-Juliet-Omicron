public class Deck {
	
	//Fields
	private int cardsUsed;
	private Card[] deck; {  //assigning 52 arrays into deck of cards
		deck = new Card[52];
	}

	public Deck() {
		int cardCt = 0;
		for (int suit = 0; suit <=3; suit++) {
			for (int value = 1; value <=13; value++ ) {
				deck[cardCt] = new Card (value, suit);
				cardCt++;
			}
		}
		cardsUsed=0;
	}


	/*randomize order of cards (shuffle) use Math.random()*/

	public void shuffle() {
		for (int i = deck.length-1; i>0; i--) { //deck.length --> remember arrays.length in prev lessons?
			int shuff = (int) (Math.random()*(i+1)); //Math.random is a "double". added (int) cast in front. Read pg 228.
			Card temp = deck[i];
			deck[i] = deck[shuff];
			deck[shuff] = temp;
		}
		cardsUsed=0;
	}



	/*keep track of cardsLeft*/

	public int cardsLeft() {
		int cardsLeft = (deck.length - cardsUsed);
		return cardsLeft;
	}

	public Card dealCard() {
		cardsUsed++;
		return deck[cardsUsed - 1];
	}
	
	public Card seeCard() {
		return deck[cardsUsed];
	}
