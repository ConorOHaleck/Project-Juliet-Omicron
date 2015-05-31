import javax.swing.JOptionPane;

public class Player {
	
	String name;
	int cash;
	int gamesPlayed;
	int gamesWon;
	
	
	Player(){
		name = JOptionPane.showInputDialog("What, good sir, is your name?");
	}
	
	Player(String nomenclature){
		name = nomenclature;
	}
	
	Player (String nomenclature, int cashola){
		name = nomenclature;
		cash = cashola;
	}
}
