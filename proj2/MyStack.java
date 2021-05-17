package proj2;
//This class implements all the basic function of a stack such as pop, top, push, etc. 
/*  @author Angel G. Romero Rosario 
    801-18-2064
*/ 

import java.util.*;

public class MyStack {
    
    public LinkedList Stack; 

    //Constructor
    public MyStack(){
        Stack = new LinkedList();
    };

    //Takes the first element of the stack and returns it
    public Object pop(){
        return Stack.removeFirst();
    }

    //This function returns the last object on the stack
    public Object topObject(){
        return Stack.peekFirst();
    }

    //Returns size of the stack
    public int getSize(){
        return Stack.size();
    }

    //Returns if the stack is or not empty
    public Boolean isEmpty(){
        return Stack.isEmpty();
    }

    //Adds an object to the stack
    public void push(Object C){
        Stack.push(C);
    }
    
}
