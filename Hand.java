import java.util.ArrayList;


public class Hand {
	
	private ArrayList<Card> stack = new ArrayList<Card>();
	private int totalVal;
	
	
	public ArrayList<Card> getHand(){
		return stack;
	}
	
	public void addCard(Card cardAdd){
		stack.add(cardAdd);
		
		totalVal = 0;
		for (int i = 0; i < stack.size(); i++) {
			totalVal += stack.get(i).getValue();
		}
		stack.trimToSize();
	}
	
	
	public int getVal(){
		return totalVal;
	}
	
	
	
}
