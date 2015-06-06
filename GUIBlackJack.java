import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.LayoutManager;


public class GUIBlackJack {

	public JFrame frame;
	
	private JButton newGameButton = new JButton("New Game");
	private GUIBlackJack kludgePass = this; //IMPORTANT! Enables the GUI to pass itself when calling a new game.
	private JTextArea consoleOut = new JTextArea();
	
	
	private final JLabel lblPlayerName = new JLabel("PLAYER NAME");
	private final static JLabel lblPlayerMoney = new JLabel("PLAYER MONEY");
	private final JButton btnHit = new JButton("Hit");
	private final JButton btnCall = new JButton("Call");
	
	private final JPanel panel1 = new JPanel((LayoutManager) null);
	private final JPanel panel2 = new JPanel((LayoutManager) null);
	private final JPanel panel3 = new JPanel((LayoutManager) null);
	private final JPanel panel4 = new JPanel((LayoutManager) null);
	
	private CardPanel cPanel1 = new CardPanel();
	private CardPanel cPanel2 = new CardPanel();
	private CardPanel cPanel3 = new CardPanel();
	private CardPanel cPanel4 = new CardPanel();

	public GUIBlackJack() {
		initialize();
	}

	private void initialize() {
		
		final Player nubCake = new Player();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1339, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cPanel1.changeCard(null);
				cPanel2.changeCard(null);
				cPanel3.changeCard(null);
				cPanel4.changeCard(null);
				
				Deck deck = new Deck();
				Hand pHand = new Hand(); //Player's Hand
				Hand dHand = new Hand(); //Dealer's Hand
				
				deck.shuffle();
				BlackjackBets.getBets();
				
				writeToConsole("The game is afoot!");
				
				dHand.addCard(deck.dealCard());
				addHandCards(dHand.getHand(), false);
				pHand.addCard(deck.dealCard());
				addHandCards(pHand.getHand(), true);
				newGameButton.setEnabled(false);
				Game.nextRound(nubCake, kludgePass, deck, pHand, dHand, false, true);;
			}
		});
		
		
		
		panel1.setBounds(140, 192, 180, 262);
		panel1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel1);
		cPanel1.setBounds(0, 0, 180, 262);
		panel1.add(cPanel1);
		
		panel2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel2.setBounds(375, 219, 180, 262);
		frame.getContentPane().add(panel2);
		cPanel2.setBounds(0, 0, 180, 262);
		panel2.add(cPanel2);
		
		panel3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel3.setBounds(113, 499, 180, 262);
		frame.getContentPane().add(panel3);
		cPanel3.setBounds(0, 0, 180, 262);
		panel3.add(cPanel3);
		
		panel4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel4.setBounds(407, 539, 180, 262);
		frame.getContentPane().add(panel4);
		cPanel4.setBounds(0, 0, 180, 262);
		panel4.add(cPanel4);
		
		
		
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
		
		
		consoleOut.setBounds(789, 61, 443, 593);
		frame.getContentPane().add(consoleOut);
		consoleOut.setText(" ");
		
		
		
		
		
			
	}
	
	
	private class CardPanel extends JPanel {
		
		Image cardImage;
		Card card;
		
		
		CardPanel(){
			cardImage = imageFind();
			}
		
		public void changeCard(Card adder){
			card = adder;
			repaint();
		}
		
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.translate(0, 0);
			int cx;
			int cy;
			
			 if (cardImage == null) {
		            g.drawString("Uh oh, something is wrong", 10, 30);
		            return;
		        }
			if (card == null){
				 cy = 4 * 261;
				 cx = 2 * 179;
			     } 
			 else {
	            cx = (card.getValue() - 1) * 179;
	            switch(card.getSuit()) {
	            case Card.HEARTS:
	                cy = 2 * 261;
	                break;
	            case Card.CLUBS:
	                cy = 0;
	                break;
	            case Card.DIAMONDS:
	                cy = 261;
	                break;
	            default:
	                cy = 3*261;
	                break;
	                }
			 }
			g.drawImage(cardImage, 0, 0, 180, 262, cx, cy, cx+180, cy+262, null);
		}
	
	public Image imageFind() {
		Image cardImage;
	       try {
	          cardImage = ImageIO.read(new File("C:/Users/RJ/Desktop/CARDS.jpg"));
	          return cardImage;
	       } catch (IOException ex) {
	            System.out.println("Oh god, oh god, we couldn't find it at all!");
	       }
	       return null;
		}
	}
	
	public void gameOff(){
		newGameButton.setEnabled(true);
		btnHit.setEnabled(false);
		btnCall.setEnabled(false);
	}
	
	public void waitForInput(final Player p1, final Deck deck, final Hand pHand, final Hand dHand){
		btnHit.setEnabled(true);
		btnCall.setEnabled(true);
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stopWaiting();
				Game.nextRound(p1, kludgePass, deck, pHand, dHand, true, false);
			}
		});
		
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopWaiting();
				Game.nextRound(p1, kludgePass, deck, pHand, dHand, false, false);
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
				switch(i){
				case 0:
					cPanel1.changeCard(toAdd.get(i));
					break;
				case 1:
					cPanel2.changeCard(toAdd.get(i));
					break;
				case 2:
					cPanel3.changeCard(toAdd.get(i));
					break;
				case 3:
					cPanel4.changeCard(toAdd.get(i));
					break;
				default:
					break;
				}
				
					
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
	public static void updateMoney() {
		lblPlayerMoney.setText("" + Player.cash);
	}
}
