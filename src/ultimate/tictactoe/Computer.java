package ultimate.tictactoe;
import java.util.Random;

/* Analysis:
    This is a class that represents the Computer playing the game.
*/

/* Design:
    The move-making algorithm is implemented here. The computer 
    makes moves completely randomly. The method returns
    an array containing the Computer's moves to prepare for the opponent. 
    This class extends the abstract Player class.
*/

public class Computer extends Player {

    // Set the variables in the Player class
    public Computer(MyMark compMark, LargeBoard thisBoard) {
            super(compMark, thisBoard);
    }
    
    // The computer makes a move randomly through this method
    // The method returns an array containing the Computer's moves
    public int[] doMove(int[] previousMove) {
        
        // Declare the variables to hold the row and column numbers of large and small boards
        int smallRow;
        int smallCol;
        int largeRow;
        int largeCol;
        
        // To assign a random number
        Random rand = new Random();
        
        // If the computer has a free move
        if (previousMove[2] == -1 || previousMove[3] == -1) {
            
            // Get the random index values
            smallRow = rand.nextInt(3);
            smallCol = rand.nextInt(3);
            largeRow = rand.nextInt(3);
            largeCol = rand.nextInt(3);
            
            // Ensure selected index is free
            while (!(isFree(smallRow, smallCol, largeRow, largeCol, myBoard))){
                smallRow = rand.nextInt(3);
                smallCol = rand.nextInt(3);
                largeRow = rand.nextInt(3);
                largeCol = rand.nextInt(3);
            }
            
        } 
        
        // If boundaries exist
        else {
            
            // Get random values for the smallBoard
            smallRow = rand.nextInt(3);
            smallCol = rand.nextInt(3);
            
            // Get the bounds for the largeBoard based on opponent's turn
            largeRow = previousMove[2];
            largeCol = previousMove[3];

            // Ensure selected index is free
            while (!(isFree(smallRow, smallCol, largeRow, largeCol, myBoard))){
                smallRow = rand.nextInt(3);
                smallCol = rand.nextInt(3);
                largeRow = previousMove[2];
                
            }
        }
        
        // Set the mark in the appropriate index
        myBoard.setMarkAt(largeRow, largeCol, smallRow, smallCol, mark);
        
        // Set move boundaries for opponent
        int[] oppBounds = {largeRow, largeCol, smallRow, smallCol};
        
        return oppBounds;
    }

}
