import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class GUIBlackJack {

	public JFrame frame;
	
	private JButton newGameButton = new JButton("New Game");
	private GUIBlackJack kludgePass = this; //IMPORTANT! Enables the GUI to pass itself when calling a new game.
	private JTextArea consoleOut = new JTextArea();
	private final JLabel lblPlayerName = new JLabel("PLAYER NAME");
	private final JLabel lblPlayerMoney = new JLabel("PLAYER MONEY");
	private final JButton btnHit = new JButton("Hit");
	private final JButton btnCall = new JButton("Call");

	public GUIBlackJack() {
		initialize();
	}

	private void initialize() {
		
		Player nubCake = new Player("Herman", 500);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Game.initialRound(nubCake, kludgePass);
				newGameButton.setEnabled(false);
			}
		});
		newGameButton.setBounds(15, 16, 115, 29);
		frame.getContentPane().add(newGameButton);
		lblPlayerName.setBounds(15, 61, 200, 50);
		lblPlayerName.setText(nubCake.name);
		
		frame.getContentPane().add(lblPlayerName);
		lblPlayerMoney.setBounds(15, 117, 200, 50);
		lblPlayerMoney.setText("" + nubCake.cash);
		
		frame.getContentPane().add(lblPlayerMoney);
		frame.getContentPane().add(btnHit);
		
		btnHit.setEnabled(false);
		btnHit.setBounds(15, 174, 115, 29);
		

		frame.getContentPane().add(btnCall);
		
		btnCall.setEnabled(false);
		btnCall.setBounds(15, 219, 115, 29);
		
		consoleOut.setRows(8);
		consoleOut.setLineWrap(true);
		consoleOut.setEditable(false);
		
		
		consoleOut.setBounds(209, 20, 443, 593);
		frame.getContentPane().add(consoleOut);
		consoleOut.setText(" ");
			
	}
	
	public void gameOff(){
		newGameButton.setEnabled(true);
	}
	
	public void waitForInput(Player p1, Deck deck, Hand pHand, Hand dHand){
		btnHit.setEnabled(true);
		btnCall.setEnabled(true);
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopWaiting();
				Game.nextRound(p1, kludgePass, deck, pHand, dHand, true);
			}
		});
		
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopWaiting();
				Game.nextRound(p1, kludgePass, deck, pHand, dHand, false);
			}
		});
		
	}
	
	public void stopWaiting(){
		btnHit.setEnabled(false);
		btnCall.setEnabled(false);
	}
	
	public void writeToConsole(String toWrite){
		consoleOut.append(toWrite);
		consoleOut.append("\n");
	}
	
	public void addHandCards(ArrayList<Card> toAdd, boolean isPlayer){
		if(isPlayer){
			for (int i = 0; i < toAdd.size(); i++) {
				consoleOut.append("Player's card is: ");
				consoleOut.append(toAdd.get(i).toString());
				consoleOut.append("\n");
			}
		}
			else{
			for (int i = 0; i < toAdd.size(); i++) {
				consoleOut.append("Dealer's card is: ");
				consoleOut.append(toAdd.get(i).toString());
				consoleOut.append("\n");
			}
			
		}
	}
}

