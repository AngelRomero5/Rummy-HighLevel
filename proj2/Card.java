// Card.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a playing card.  Uses classes Rank and Suit for
// expressing the card value.
package proj2;
import javax.swing.*;



/**
 * Representation of a single playing card. A card consists of a suit value
 * (e.g. hearts, spades, diamonds, clubs), a rank value (e.g. ace, 7, king), and an image of
 * the front of the card.  A card object is immutable; once instantiated, the
 * values cannot change.
 *
 * @author John K. Estell adapted by Patti Ord��ez 
 * @version 1.0
 */
public class Card implements Comparable {

   // instance variables for the card
   private char suitValue;  
   private char rankValue;
   final static char[] suit = {'c','d','h','s'};
   final static char [] rank = {'a','2','3','4','5','6','7','8','9','t','j','q','k'};


   private ImageIcon cardImage;

   static String directory = "cards/";


  /**
   * Creates a new playing card.
   * @param suit the suit value of this card.
   * @param rank the rank value of this card.
   * @param cardFace the face image of this card.
   */
   public Card( char suit, char rank, ImageIcon cardFace ) { //This constructor receives rank, icon and the icon. It creates the card object
      cardImage = cardFace;
      suitValue = suit;
      rankValue = rank;
	  }


   public Card(char suit,char rank) //This constructor receives suit and rank. 
                                    //In the function it creates the card object and uses a function to get the card’s icon.
   {
	   suitValue = suit;
	   rankValue = rank;
	   cardImage = new ImageIcon(getImageFile());
   }

   //This function receives a char suit and returns an index number depending on the suit. 
   public static int getSuitIndex(char suit)  
   {

					switch(suit){
					case 'c':
						return 0;

					case 'd':
						return 1;

					case 'h':
						return 2;

					case 's':
						return 3;
				}
				return -1;

	}

   //This function receives a char rank and returns an index number depending on the rank. 
	public static int getRankIndex(char rank)  
	{
					switch (rank){
					case 'a':
						return 0;
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						return rank - '1';
					case 't':
						return 9;
					case 'j':
						return 10;
					case 'q':
						return 11;
					case 'k':
						return 12;
					default:
						return -1;
				}
	}


   //Busca la carta en específico
	public String getImageFile(){
		return directory + toString() + ".gif";
	}


  /**
   * Returns the suit of the card.
   * @return a Suit constant representing the suit value of the card.
   */
   public char getSuit() {
      return suitValue;
   }


  /**
   * Returns the rank of the card.
   * @return a Rank constant representing the rank value of the card.
   */
   public char getRank() {
      return rankValue;
   }


  /**
   * Returns the graphic image of the card.
   * @return an icon containing the graphic image of the card.
   */
   public ImageIcon getCardImage() {
      return cardImage;
   }


  /**
   * Returns a description of this card.
   * @return the name of the card.
   */
   public String toString() {
		return "" + getRank() + getSuit();
   }


  /**
   * Compares two cards for the purposes of sorting.
   * Cards are ordered by their
   * rank value.
   * @param otherCardObject the other card
   * @return a negative integer, zero, or a positive integer is this card is
   * less than, equal to, or greater than the referenced card.
   */
   public int compareTo( Object otherCardObject ) {
      Card otherCard = (Card) otherCardObject;
	  int rankDiff = getRankIndex(rankValue) - getRankIndex(otherCard.rankValue);
	  return rankDiff;
   }
}
