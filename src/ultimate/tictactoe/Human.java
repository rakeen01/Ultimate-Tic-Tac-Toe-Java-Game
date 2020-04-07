package ultimate.tictactoe;
import java.util.Scanner;

/* Analysis:
    This is a class which represents a Human playing the game.
*/

/* Design:
    The move-selecting algorithm is implemented here. The user's selected moves
    are tested to check whether it is valid, and the method decides whether the
    user gets a free pass or has boundaries to make the move in. The method returns
    an array containing the user's moves to prepare for the opponent. This class extends the
    abstract Player class.
*/

public class Human extends Player {

    // Overloaded constructor for the human player
    // Passes in the ultimate TTT board and the current player's choice of mark
    // Calls the super constructor to intialize the variables
    public Human(MyMark playerMark, LargeBoard thisBoard) {
        super(playerMark, thisBoard);
    }
    
    // Check if there are restrictions
    private boolean areThereBounds(int[] previousMove){
        
        if(previousMove[0] == -1 && previousMove[1] == -1 && previousMove[2] == -1 && previousMove[3] == -1){
            return false;
        }
        else{
            return true;
        }
    }
    
    // This method allows the user to select an index to place a mark in
    // The method returns an array containing the user's moves
    public int[] doMove(int[] previousMove) {

        Scanner input = new Scanner(System.in);
        
        // Define variables
        int boardNum = -1;
        int largeUserRow = -1;
        int largeUserCol = -1;
        int smallUserRow = -1;
        int smallUserCol = -1;
        
        // If there are no restrictions
        if(!areThereBounds(previousMove)){
            
            do{
                // Get board number
                System.out.println("Indicate the board number where your next move will be: ");
                boardNum = input.nextInt();

                // Get largeBoard row and col from board number
                if(boardNum == 1){
                    largeUserRow = 0;
                    largeUserCol = 0;
                }
                else if (boardNum == 2){
                    largeUserRow = 0;
                    largeUserCol = 1;
                }
                else if (boardNum == 3){
                    largeUserRow = 0;
                    largeUserCol = 2;
                }
                else if (boardNum == 4){
                    largeUserRow = 1;
                    largeUserCol = 0;
                }
                else if (boardNum == 5){
                    largeUserRow = 1;
                    largeUserCol = 1;
                }
                else if (boardNum == 6){
                    largeUserRow = 1;
                    largeUserCol = 2;
                }
                else if (boardNum == 7){
                    largeUserRow = 2;
                    largeUserCol = 0;
                }
                else if (boardNum == 8){
                    largeUserRow = 2;
                    largeUserCol = 1;
                }
                else if (boardNum == 9){
                    largeUserRow = 2;
                    largeUserCol = 2;
                }
                else {
                    System.out.println("You have selected an invalid board! Try again.");
                }
                
            } while(boardNum < 1 || boardNum > 9); // Input validation
            
            System.out.println("You are playing in Board number " + boardNum);
            
        }
        
        // Restrictions exist
        else{
            // Opponents small board index is user's large board index.
            largeUserRow = previousMove[2];
            largeUserCol = previousMove[3];
        }
        
        
        // Get human player's choice of row number
        System.out.println("Indicate the row number of your next move in this board: ");
        smallUserRow = input.nextInt();
        
        while(smallUserRow < 0 || smallUserRow > 2){
            System.out.println("Invalid selection, try again. (Hint: Look at the board row numbers!)");
            System.out.println("Indicate the row number of your next move in this board: ");
            smallUserRow = input.nextInt();
        }
        
        // Get human player's choice of column number
        System.out.println("Indicate the column number of your next move in this board: ");
        smallUserCol = input.nextInt();
        
        while(smallUserCol < 0 || smallUserCol > 2){
            System.out.println("Invalid selection, try again. (Hint: Look at the board column numbers!)");
            System.out.println("Indicate the column number of your next move in this board: ");
            smallUserCol = input.nextInt();
        }

        // Put the selections in an array
        int[] myMove = {largeUserRow, largeUserCol, smallUserRow, smallUserCol};
        
        // Input validation
        while (!isFree(smallUserRow, smallUserCol, largeUserRow, largeUserCol, myBoard)) {
            System.out.println("The index you selected is already taken! Look at the board and try again.");
            
            // If there are no restrictions
            if(!areThereBounds(previousMove)){

                do{
                    // Get board number
                    System.out.println("Indicate the board number where your next move will be: ");
                    boardNum = input.nextInt();

                    // Get largeBoard row and col from board number
                    if(boardNum == 1){
                        largeUserRow = 0;
                        largeUserCol = 0;
                    }
                    else if (boardNum == 2){
                        largeUserRow = 0;
                        largeUserCol = 1;
                    }
                    else if (boardNum == 3){
                        largeUserRow = 0;
                        largeUserCol = 2;
                    }
                    else if (boardNum == 4){
                        largeUserRow = 1;
                        largeUserCol = 0;
                    }
                    else if (boardNum == 5){
                        largeUserRow = 1;
                        largeUserCol = 1;
                    }
                    else if (boardNum == 6){
                        largeUserRow = 1;
                        largeUserCol = 2;
                    }
                    else if (boardNum == 7){
                        largeUserRow = 2;
                        largeUserCol = 0;
                    }
                    else if (boardNum == 8){
                        largeUserRow = 2;
                        largeUserCol = 1;
                    }
                    else if (boardNum == 9){
                        largeUserRow = 2;
                        largeUserCol = 2;
                    }
                    else {
                        System.out.println("You have selected an invalid board! Try again.");
                    }

                } while(boardNum < 1 || boardNum > 9); // Input validation

                System.out.println("You are playing in Board number " + boardNum);

            }

            // Restrictions exist
            else{
                // Opponents small board index is user's large board index.
                largeUserRow = previousMove[2];
                largeUserCol = previousMove[3];
            }


            // Get human player's choice of row number
            System.out.println("Indicate the row number of your next move in this board: ");
            smallUserRow = input.nextInt();

            while(smallUserRow < 0 || smallUserRow > 2){
                System.out.println("Invalid selection, try again. (Hint: Look at the board row numbers!)");
                System.out.println("Indicate the row number of your next move in this board: ");
                smallUserRow = input.nextInt();
            }

            // Get human player's choice of column number
            System.out.println("Indicate the column number of your next move in this board: ");
            smallUserCol = input.nextInt();

            while(smallUserCol < 0 || smallUserCol > 2){
                System.out.println("Invalid selection, try again. (Hint: Look at the board column numbers!)");
                System.out.println("Indicate the column number of your next move in this board: ");
                smallUserCol = input.nextInt();
            }
            
            // Put the selections in an array
            myMove[0] = largeUserRow;
            myMove[1] = largeUserCol;
            myMove[2] = smallUserRow;
            myMove[3] = smallUserCol;
        }
        
        // Set the mark in the valid position
        myBoard.setMarkAt(largeUserRow, largeUserCol, smallUserRow, smallUserCol, mark);
            
        // Return the array containing the human's index information
        return myMove;
    }
}
