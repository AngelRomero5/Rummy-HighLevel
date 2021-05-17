package proj2;
/*

 The Pile class serves as the stack where the cards are going to be discarded from the hand of the players.
 @author Created by Angel G. Romero Rosario
*/

public class Pile extends MyStack{

    private MyStack MyPile; 

    //Constructor
    public Pile(){
        MyPile = new MyStack();
    };

    // Aqui intenté hacer singleton 
    // private static Pile MyPile = new Pile();
    // // private int cardCount = 0;

    //     private Pile(){  
    //     }; //Private Constructor so there can only be one pile 

    //     public static Pile getPile(){
    //         return MyPile;
    //     }

    //This function returns the first card object of the pile 
    public Card getFirstCard(){
        return (Card)MyPile.pop();
    }

    //Returns first card but doesn't remove
    public Card viewFirstCard(){
        return (Card)MyPile.topObject();
    }

    //Returns if pile is empty or not
    public Boolean isPileEmpty(){
        return MyPile.isEmpty();
    }

    //Adds card to pile
    public void addCardtoPile(Card C){
        MyPile.push(C);
    }
}
