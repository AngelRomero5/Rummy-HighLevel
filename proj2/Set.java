package proj2;
import java.util.*;

// This class implements where the sets are going to be placed when discarded
// Modified by Angel G. Romero Rosario


public class Set implements SetInterface 
{
    /**
     * Creates a hand for a set.
     */
	char rank;
	int rankIndex;
	protected List handSet = new ArrayList();
   
    public Set(char rank) {
      
      super();
		rankIndex = Card.getRankIndex(rank);
		this.rank = rank;
    }

    public boolean isFull(){
		return handSet.size() == 4;
	}
    public void addCard( Card card ) {
		if (rankIndex == Card.getRankIndex(card.getRank()))
			handSet.add(card);
    }

	 /**
   * Obtains the card stored at the specified location in the hand.  Does not
   * remove the card from the hand.
   * @param index position of card to be accessed.
   * @return the card of interest, or the null reference if the index is out of
   * bounds.
   */
   public Card getCard( int index ) {
      return (Card) handSet.get( index );
   }
   
    /**
   * Removes the specified card from the current hand.
   * @param card the card to be removed.
   * @return the card removed from the hand, or null if the card
   * was not present in the hand.
   */
   public Card removeCard( Card card ) {
      int index = handSet.indexOf( card );
      if ( index < 0 )
         return null;
      else
         return (Card) handSet.remove( index );
   }

  /**
   * The number of cards held in the hand.
   * @return number of cards currently held in the hand.
   */
   public int getNumberOfCards() {
      return handSet.size();
   }


  /**
   * Sorts the card in the hand.
   * Sort is performed according to the order specified in the {@link Card} class.
   */
   public void sort() {
      Collections.sort( handSet );
   }


  /**
   * Checks to see if the hand is empty.
   * @return <code>true</code> is the hand is empty.
   */
   public boolean isEmpty() {
      return handSet.isEmpty();
   }


  /**
   * Determines whether or not the hand contains the specified card.
   * @param card the card being searched for in the hand.
   * @return <code>true</code> if the card is present in the hand.
   */
   public boolean containsCard( Card card ) {
      return false;
   }


  /**
   * Searches for the first instance of the specified card in the hand.
   * @param card card being searched for.
   * @return position index of card if found, or <code>-1</code> if not found.
   */
   public int findCard( Card card ) {
      return handSet.indexOf( card );
   }

  /**
   * Removes the card at the specified index from the hand.
   * @param index poisition of the card to be removed.
   * @return the card removed from the hand, or the null reference if
   * the index is out of bounds.
   */
   public Card removeCard( int index ) {
      return (Card) handSet.remove( index );
   }

	public int getRankIndex()
	{
		return rankIndex;
	}
	
	public char getRank()
	{
		return rank;
	}
	
	public int compareTo( Object otherHandObject )
	{
		Set otherSet = (Set) otherHandObject;
		return rankIndex - otherSet.rankIndex;
	}
    /**
     * Evaluates a hand according to the rules of the card game.
     * Each card is worth its displayed rank value (ace = 1, two = 2, etc.)
     * in points with face cards worth ten points.  The value of a hand
     * is equal to the summation of the points of all the cards held in
     * the hand.
     */
    public int evaluateHand() {
        int value = 0;

        int cardValue = rankIndex - Card.getRankIndex('a') + 1;
        if ( cardValue > 10 )
           cardValue = 10;
        value = getNumberOfCards() * cardValue;

        return value;
    }

}