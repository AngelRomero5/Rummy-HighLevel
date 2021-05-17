package proj2;
/*  
   editado por Angel G. Romero Rosario
   801-18-2064

   Aqui ocurre toda la magia. Aqui se implementan todas las funciones del proyecto. 
   No hay un loop, todo ocurre dentro del actionListener una vez creada la tabla.
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener
{
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Pile stackPile;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1LaySet;
	JButton p2LaySet;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	JButton p1Run;
	JButton p2Run;

	Hand p1Hand;
	Hand p2Hand;

	// Observer: Los jugadores "observan" al deck (Que en este caso podria ser el ciente) y el table actualiza 
	// ese deck (sirve como mediador entre jugadores (observers) y deck(client)).
	// Singleton podrian ser individualmente el stack, el deck, el hand, etc. ¿Se crean Protected y hay un solo objeto en esa clase?

	//Reparte las cartas
	private void deal(Card [] cards) 
	{
		for(int i = 0; i < cards.length; i ++)
			cards[i] = (Card)cardDeck.dealCard();
	}

	public Table() 
	{
		super("Welcome to the Hardest Game to Make in Java of the Century");

		setLayout(new BorderLayout());
		setSize(1200,700);

		//This listener allows the user to terminate the program once the exit button is pressed
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {      
				System.out.println("\nClosing the game. Goodbye!");
				dispose();             
			}
		});

		cardDeck = new Deck();
	    
		Card topCard = cardDeck.dealCard(); //Este objeto contiene una carta random para ponerla en el tope del stack

		stackPile = new Pile();			    //Esto es la pila nueva

		stackPile.addCardtoPile(topCard);	//Añado la carta fisicamente al stack

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++){
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));
		}

		//Paneles de arriba
		//Forma mas efectiva que nombrarlos uno a uno
		for(int i=0; i < 4;i++){
			top.add(setPanels[i]);
		}


		player1 = new JPanel();
		player1.add(top);


		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();

		//Paneles de abajo
		//Forma mas efectiva que nombrarlos uno a uno
		for(int i=4;i < 9;i++){
			bottom.add(setPanels[i]);
		}
		

		player2 = new JPanel();
		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1LaySet = new JButton("Lay Set"); //If the player cannot make a set, it does nothing
		p1LaySet.addActionListener(this);
		p1LayOnStack = new JButton("LayOnPile");
		p1LayOnStack.addActionListener(this);


		Card [] cardsPlayer1 = new Card[numDealtCards];
		deal(cardsPlayer1);

		//In this loop, the program writes in a text file the game output
		for(int i=0; i < 9; i++){

			try{
				PrintWriter os = new PrintWriter(new FileWriter("test.txt", true)); //Create file if not exists otherwise, 
																					//just opens the file
				
				if(i==0){
					os.write("Romi Card Game: " + "\n\n");							//Initial hand for player
					os.write("Initial Hand for P1: ");
				}	
				os.write(cardsPlayer1[i] + ", ");
				if(i==8) os.write("\n");
				os.close();
	
			}catch(IOException e){
				System.out.println("Error writing file"); //Throws error if file didnt open
			}
		}

		p1Hand = new Hand();
		for(int i = 0; i < cardsPlayer1.length; i++){
			p1Hand.addCard(cardsPlayer1[i]);
		}
		p1HandPile = new JList(p1Hand.getDefaultListModel()); //Hand object


		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1LaySet, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Pile");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		
		topOfStack.setIcon(new ImageIcon(Card.directory + topCard.toString() + ".gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2LaySet = new JButton("Lay Set");
		p2LaySet.addActionListener(this);
		p2LayOnStack = new JButton("LayOnPile");
		p2LayOnStack.addActionListener(this);

		Card [] cardsPlayer2 = new Card[numDealtCards];
		deal(cardsPlayer2);

		//In this loop, the cards from the player's hand are written
		//out in a external file
		for(int i=0; i < 9; i++){

			try{
				PrintWriter os = new PrintWriter(new FileWriter("test.txt", true));
				
				if(i==0){
					os.write("Initial Hand for P2: ");
				}	
				os.write(cardsPlayer2[i] + ", ");
				if(i==8) os.write("\n");

				os.close();
	
			}catch(IOException e){
				System.out.println("Error writing file.");
			}
		}

		p2Hand = new Hand();

		for(int i = 0; i < cardsPlayer2.length; i++){
			p2Hand.addCard(cardsPlayer2[i]);
		}
			

		p2HandPile = new JList(p2Hand.getDefaultListModel());

		middle.add(new HandPanel("Future Bot", p2HandPile, p2Stack, p2Deck, p2LaySet, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);
	}

		//Esta funcion imita a un jugador automatizado. 
		//Primero toma una carta del stack o del deck 
		//y la añade al hand. Luego verifica si puede 
		//hacer un un set y lo descarta.
		//Si no puede hacer más, finaliza su turno 
		//poniendo una carta en el stackPile

	public void automatedPlayer(){

		//En esta parte, se genera un numero random, dependiendo de si el numero es par o impar 
		//el jugador automatico tomará una carta del stack o del deck

		Random rnum = new Random(); //Genera un numero random 
		int upperbound = 10;
		int randomNumber = rnum.nextInt(upperbound);

		Boolean playerlayed = false;   //Checks if player finished laying cards
			
		if(randomNumber % 2 == 0 && !cardDeck.isEmpty()){
			
			Card card = cardDeck.dealCard();
			
				if (card != null){ 
					p2Hand.addCard(card);
				}
				if(cardDeck.isEmpty())
					deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		}
		else if(!stackPile.isEmpty()){
			
			Card card = stackPile.getFirstCard();
			
			if(card != null){
				Card topCard = stackPile.viewFirstCard();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
			
					p2Hand.addCard(card); 
			}
		}

		//In this while, the player can make sets  
		//When finshed it returns true and allows player 
		//to lay the card on the stackPile to finish the player's turn

		while(!playerlayed){

			Vector<Card> handSet = p2Hand.findSet(); //Search a set
			if(handSet != null){
				for(Card c :handSet){ //For every card on set, lay it 
					layCard(c);
					p2Hand.removeCard(c);
				}
			}
			else {  //If it doesnt find a set
					//Genera un numero random menor a la cantidad de cartas en el Hand
				int RandomLay = rnum.nextInt(p2Hand.getNumberOfCards());  
				Card C = p2Hand.removeCard(RandomLay);
				stackPile.addCardtoPile(C); 			//Descarta la carta en la pila
				topOfStack.setIcon(C.getCardImage());
				playerlayed = true; 					//Turn's over
			}
		}
	}

	//This Boolean controls the player's turn
	Boolean tookCard = true; 	//If the player took a card from Deck

	public void actionPerformed(ActionEvent e) 
	{
		//This closes the game when deck is empty or 
		//if a player's hand is empty
		if(cardDeck.isEmpty() || p1Hand.isEmpty() || p2Hand.isEmpty()){
			DisplayWinner();
		}

		Object src = e.getSource();
		if(p1Deck == src && tookCard){ 

			Card card = cardDeck.dealCard();

			if (card != null){
				p1Hand.addCard(card);
				tookCard = false;
			}

			if(cardDeck.isEmpty())
				deckPile.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		}

		if(p1Stack == src && tookCard){

			Card card = stackPile.getFirstCard();
			tookCard = false;

			if(card != null){
				Card topCard = stackPile.viewFirstCard();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

				p1Hand.addCard(card);
			}
		}

		
		//This action allows the player to check if their are 
		//sets of 3 or 4 cards in player's hand
		if(p1LaySet == src && !tookCard){
			Vector<Card> handSet = p1Hand.findSet();
			if(handSet != null){
				for(Card c :handSet){ //For every card on set, lay it 
					layCard(c);
					p1Hand.removeCard(c);
				}
			}
		}

		if(p1LayOnStack == src && !tookCard){

			tookCard = true;
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{
					Card card = (Card)obj;
					p1Hand.removeCard(card);
					stackPile.addCardtoPile(card);
					topOfStack.setIcon(card.getCardImage());
					automatedPlayer();		//Bot's turn
				}
			}
		}
	}

	//This function lets the player lay a card 
	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		// setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());

		//Every time a card is layed, write the card on the output File
		try{
			PrintWriter os = new PrintWriter(new FileWriter("test.txt", true)); //Open file

			os.write("laying " + card);
			os.write("\n");
			os.close();

		}catch(IOException e){
			System.out.println("Exception");
		}
	}

	//This function evaluates each player's hand and 
	//then displays what player won the game
	public void DisplayWinner(){

		String Winner = " "; //Empty string
	
				if(p1Hand.evaluateHand() < p2Hand.evaluateHand()){
					JOptionPane.showMessageDialog(this, "Player1 won!", 
									   "Deck is Empty", JOptionPane.INFORMATION_MESSAGE);
					Winner = "Player 1";
				}
				else{
					JOptionPane.showMessageDialog(this, "Player2 won!", 
									   "Deck is Empty", JOptionPane.INFORMATION_MESSAGE);
					Winner = "Player 2";
				}

				//Write in the file the final output
				try{
					PrintWriter os = new PrintWriter(new FileWriter("test.txt", true));
					
					os.write("Final hand for P1: ");
					os.write(p1Hand.toString() + "\n");
					os.write("Final hand for P2: ");
					os.write(p2Hand.toString() + "\n");
					os.write("The winner is " + Winner);
					os.close();
		
				}catch(IOException w){
					System.out.println("Error writing file.");
				}
	
				System.out.println("Deck is empty!");
				System.out.println("Game over. "+ Winner +"won!");
				dispose();
	}
	
}

class HandPanel extends JPanel
{

	public HandPanel(String name, JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);

		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
		//add(Box.createGlue());
		add(stack);

		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
		//add(Box.createGlue());
		add(deck);

		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);

		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}
}

class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[5];

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}