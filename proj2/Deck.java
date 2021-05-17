package proj2;
/*This class implements a Deck object full of Card objects. It allows players to take cards from the top.
* 
*
*
*
* @author John K. Estell adapted by Patti Ordóñez modified by Ángel G. Romero Rosario
*/

import java.util.*;

public class Deck { //It is a LinkedList(Stack) that contains the 52 card objects.

   LinkedList deck; 
   private int index; 

  /**
   * Creates an empty deck of cards.
   */
   //This constructor creates the deck with a LinkedList
   
   public Deck() {
      deck = new LinkedList();
      for(int i = 0; i < Card.suit.length; i++){ 				 //Para cada suit llena todos los ranks
			for(int j = 0; j < Card.rank.length; j++){ 			 //Llena cada rank
				Card card = new Card(Card.suit[i],Card.rank[j]); //Crea el objecto Card 
				addCard(card);                                   //Añade al deck
			}
		}
      shuffle();
   }

	public Card peek() //This function lets the user “see” the last card on Deck object.
	{
		if(deck.size() == 0)
			return null;
		else
			return (Card)deck.getLast();
	}

   public void addCard(Card card) { //This function adds a card to the Deck object. 
      deck.add( card );
   }


   public int getSizeOfDeck() {  //This function returns the size of the Deck
      return deck.size();
   }

   public Card dealCard() { //This function returns the top card on the Deck object.
   
	 if ( deck.size() == 0 )
         return null;
      else
       
		return (Card) deck.removeFirst();
   }

   public Card removeCard() {  //This function returns the bottom card on the Deck object.
 
	if (deck.size() == 0)
         return null;
      else{
		
         return (Card) deck.removeLast();
	}
   }


  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() {
      Collections.shuffle( deck );
   }


  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty() {
		return deck.size() == 0;
   }



  /**
   * Restores the deck to "full deck" status.
   */
  public void restoreDeck() {
	//not sure if kosher
      deck.removeAll(deck);
   }
   

}
