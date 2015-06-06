import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Game {

	public static void main(String[] args) {

		//All this does is call the GUI, I kept in code from the windowbuilder, just moved it here.

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBlackJack window = new GUIBlackJack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static void nextRound(Player p1, GUIBlackJack window, Deck deck, Hand pHand, Hand dHand, boolean isHit, boolean isFirst){

		if(isHit){
			pHand.addCard(deck.dealCard());
		}

		if(dHand.getVal() < 10){
			dHand.addCard(deck.dealCard());
		}

		window.addHandCards(dHand.getHand(), false);

		window.addHandCards(pHand.getHand(), true);
		if(isFirst != true){
			if(victoryCheck(pHand, dHand, isHit)){
				window.writeToConsole("You won!");
				JOptionPane.showMessageDialog(null, BlackjackBets.ifWin());
				GUIBlackJack.updateMoney();
				p1.gamesPlayed += 1;
				p1.gamesWon += 1;
				window.gameOff();
			}
			else if(defeatCheck(pHand, dHand, isHit)){
				window.writeToConsole("You lost!");
				JOptionPane.showMessageDialog(null, BlackjackBets.ifLose());
				GUIBlackJack.updateMoney();
				p1.gamesPlayed += 1;
				window.gameOff();
				if (Player.cash == 0) {
					JOptionPane.showMessageDialog(null, "You are out of money. Goodbye!");
					System.exit(0);

				}
			}
			else{
				window.writeToConsole("What are you going to do now?");
				window.waitForInput(p1, deck, pHand, dHand);
			}
		}
		
		else{
			window.writeToConsole("What are you going to do now?");
			window.waitForInput(p1, deck, pHand, dHand);
		} 


	}

	public static boolean victoryCheck(Hand pHand, Hand dHand, boolean hitState){
		if(hitState){
			if(dHand.getVal() > 21){
				defaultVictory();
				return true;
			}
			return false;
		}
		if(pHand.getVal() > dHand.getVal()){
			higherVictory();
			return true;
		}
		return false;

	}

	public static boolean defeatCheck(Hand pHand, Hand dHand, boolean hitState){
		if(hitState){
			if(pHand.getVal() > 21){
				return true;
			}
			return false;
		}
		if(dHand.getVal() > pHand.getVal()){
			return true;
		}
		return false;
	}

	public static void defaultVictory(){
		System.out.println("The dealer went over 21! YOU WON!");
	}

	public static void higherVictory(){
		System.out.println("You got a higher total than the dealer! YOU WON!");
	}

	public static void defaultDefeat(){
		System.out.println("You went over 21! YOU LOST!");
	}

	public static void higherDefeat(){
		System.out.println("The dealer got a higher total than you! YOU LOST!");
	}

}
