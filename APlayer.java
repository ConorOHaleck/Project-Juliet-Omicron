import javax.swing.JOptionPane;

public class Player {
	
	String name;
	public static double cash;
	int gamesPlayed;
	int gamesWon;
	
	
	Player(){
		try{
		name = JOptionPane.showInputDialog("What, good sir, is your name?");
		String c = JOptionPane.showInputDialog("How much would you like to set aside for betting?");
		cash = Double.parseDouble(c);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Goodbye!");
			System.exit(1);
		}
	}
	
	/*Player(String nomenclature){
		name = nomenclature;
	}
	
	Player (String nomenclature, int cashola){
		name = nomenclature;
		cash = cashola;
	}*/
}
