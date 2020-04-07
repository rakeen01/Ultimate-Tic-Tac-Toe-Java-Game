package ultimate.tictactoe;
import java.util.Iterator;

/* Analysis:
    This is class represents a simulation of a game of
    ultimate TTT. It utilizes the other classes to simulate
    the game.
*/

/* Design:
    The constructor initializes the player objects and the board the game will take place
    in. Various checker methods check for a winner, loser or tie for each small board and
    the overall game to make runtime decisions. Various print methods communicate with each
    other to print the board after each turn. The run() and itsMyTurn() methods are the most
    important: run() is the 'parent' method which drives the game, called by the DriverMain 
    class, while itsMyTurn() simulates each turn in the game based on the Players involved.
*/

public class MyGame {

    // Declare variables holding the player and
    // board objects
    private Player playerX = null;
    private Player playerO = null;
    private LargeBoard ultimateBoard = null;
    
    // Overloaded constructor that initializes the board and the
    // two players
    public MyGame(Player x, Player o, LargeBoard thisBoard) {
        playerX = x;
        playerO = o;
        ultimateBoard = thisBoard;
    }

    // Print the name of the board the player must make a move in
    private String boardName(int largeRow, int largeCol) {
        
        if(largeRow == 0 && largeCol == 0){
            return "Board 1";
        }
        else if (largeRow == 0 && largeCol == 1){
            return "Board 2";
        }
        else if (largeRow == 0 && largeCol == 2){
            return "Board 3";
        }
        else if (largeRow == 1 && largeCol == 0){
            return "Board 4";
        }
        else if (largeRow == 1 && largeCol == 1){
            return "Board 5";
        }
        else if (largeRow == 1 && largeCol == 2){
            return "Board 6";
        }
        else if (largeRow == 2 && largeCol == 0){
            return "Board 7";
        }
        else if (largeRow == 2 && largeCol == 1){
            return "Board 8";
        }
        else if (largeRow == 2 && largeCol == 2){
            return "Board 9";
        }
        else {
            return "any board you like!";
        }
    }

    
    // Check which player won the small board at a given index
    private boolean checkSmallBoardWinner(int largeRow, int largeCol, Player thisPlayer) {
        
        // Check each column of the small board
        for (int smallCol = 0; smallCol < 3; smallCol++) {
            if (ultimateBoard.getMarkFrom(largeRow, largeCol, 0, smallCol).getMark().equals(thisPlayer.mark.getMark())
            && ultimateBoard.getMarkFrom(largeRow, largeCol, 1, smallCol).getMark().equals(thisPlayer.mark.getMark())
            && ultimateBoard.getMarkFrom(largeRow, largeCol, 2, smallCol).getMark().equals(thisPlayer.mark.getMark())) {
                return true;
            }

        }
        
        // Check each row of of the small board
        for (int smallRow = 0; smallRow < 3; smallRow++) {
            if (ultimateBoard.getMarkFrom(largeRow, largeCol, smallRow, 0).getMark().equals(thisPlayer.mark.getMark())
            && ultimateBoard.getMarkFrom(largeRow, largeCol, smallRow, 1).getMark().equals(thisPlayer.mark.getMark())
            && ultimateBoard.getMarkFrom(largeRow, largeCol, smallRow, 2).getMark().equals(thisPlayer.mark.getMark())) {
                return true;
            }

        }
            
        // Check diagonals of the small board
        if ((ultimateBoard.getMarkFrom(largeRow, largeCol, 0, 0).getMark().equals(thisPlayer.mark.getMark())
        && ultimateBoard.getMarkFrom(largeRow, largeCol, 1, 1).getMark().equals(thisPlayer.mark.getMark())
        && ultimateBoard.getMarkFrom(largeRow, largeCol, 2, 2).getMark().equals(thisPlayer.mark.getMark()))
        || (ultimateBoard.getMarkFrom(largeRow, largeCol, 2, 0).getMark().equals(thisPlayer.mark.getMark())
        && ultimateBoard.getMarkFrom(largeRow, largeCol, 1, 1).getMark().equals(thisPlayer.mark.getMark())
        && ultimateBoard.getMarkFrom(largeRow, largeCol, 0, 2).getMark().equals(thisPlayer.mark.getMark()))) {
            return true;
        }
            
        // No winner yet
        return false;
    }
    
    // Checks if a player won the ultimate tictactoe game
    private boolean checkMainWinner(Player thisPlayer) {
        
        // Check each column of the large board
        for (int largeCol = 0; largeCol < 3; largeCol++) {
            if ((checkSmallBoardWinner(0, largeCol, thisPlayer) && checkSmallBoardWinner(1, largeCol, thisPlayer) 
            && checkSmallBoardWinner(2, largeCol, thisPlayer))) {
                return true;
            }
        }
        
        // Check each row of the large board
        for (int largeRow = 0; largeRow < 3; largeRow++) {
            if ((checkSmallBoardWinner(largeRow, 0, thisPlayer) && checkSmallBoardWinner(largeRow, 1, thisPlayer) 
            && checkSmallBoardWinner(largeRow, 2, thisPlayer))) {
                return true;
            }
        }

        // Check diagonals of the large board
        if ((checkSmallBoardWinner(0, 0, thisPlayer) && checkSmallBoardWinner(1, 1, thisPlayer) && checkSmallBoardWinner(2, 2, thisPlayer)) 
        || (checkSmallBoardWinner(2, 0, thisPlayer) && checkSmallBoardWinner(1, 1, thisPlayer) && checkSmallBoardWinner(0, 2, thisPlayer))) {
            return true;
        }
        
        // No main winner yet
        return false;
    }

    // Prints the status of each small board (win, lose or full)
    private int[] printBoard(int[] previousMove) {
        System.out.println(ultimateBoard.storeBoard());
        
        // Do not print anything if moves have not been made
        if (previousMove[0] == -1){
            return previousMove;
        }
        
        // Check if X won
        if (checkSmallBoardWinner(previousMove[0], previousMove[1], playerX)) {
            System.out.println("X won " + boardName(previousMove[0], previousMove[1]) + "\n\n");
            ultimateBoard.getSmallBoard(previousMove[0], previousMove[1]).setWinner("X");
            return previousMove;
        } 
        
        // Check if O won
        else if (checkSmallBoardWinner(previousMove[0], previousMove[1], playerO)) {
            System.out.println("O won " + boardName(previousMove[0], previousMove[1]) + "\n\n");
            ultimateBoard.getSmallBoard(previousMove[0], previousMove[1]).setWinner("O");
            return previousMove;
        } 
        
        // Check if the board is full but nobody won, indicating a tie
        else if (isFull(previousMove[0], previousMove[1])) {
            System.out.println("The " + boardName(previousMove[0], previousMove[1]) + " is full.\n\n");
            ultimateBoard.getSmallBoard(previousMove[0], previousMove[1]).setWinner("F");
            return previousMove;
            
           /* // Free pass for opponent
            int[] freePass = {-1,-1,-1,-1};
            return freePass;*/
        }
        
        return previousMove;
    }
    
    // Checks whether the ultimate game was a tie or not
    private boolean checkTie() {
        Iterator look = ultimateBoard.iterator();
        while (look.hasNext()) {
            if (((SmallBoard) look.next()).getWinner().equals("-")) {
                return false;
            }
        }
        return true;
    }

    // Returns true if the board has no empty spaces left
    private boolean isFull(int largeRow, int largeCol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ultimateBoard.getSmallBoard(largeRow, largeCol).getMarkFrom(i, j).getMark().equals("-")){
                    return false;
                }
            }
        }
        return true;
    }
    
    // This method is essentially the method which drives the game, called in DriverMain
    public void run() {
        
        // Initialization of {-1,-1,-1,-1} means that player has a free pass to place
        // mark anywhere. This happens on the first turn or if the small board is full.
        int[] myBounds = {-1,-1,-1,-1};
        myBounds = printBoard(myBounds);
        
        // As long as there is no winner or tie, loop game
        while (!(checkMainWinner(playerX)) && !(checkMainWinner(playerO)) && !(checkTie())) {
            
            // If it is a tie, print this and end loop
            if (checkTie()) {
                System.out.println("Congratulations to both players, the game was a tie!");
                return;
            }
            
            // Opponent 1's turn
            myBounds = itsMyTurn(myBounds, true);
            myBounds = printBoard(myBounds);
            
            // Check for winner, loser or tie
            if (!checkMainWinner(playerX) && !checkMainWinner(playerO)) {
                if (checkTie()) {
                    System.out.println("Congratulations to both players, the game was a tie!");
                    return;
                }
                
                // If there is no winner, loser or tie, it's opponent 2's turn
                myBounds = itsMyTurn(myBounds, false);
                myBounds = printBoard(myBounds);
            }
        }

        // If X won print this
        if (checkMainWinner(playerX)) {
            System.out.println("Congratulations, Player X won!");
        } 
        
        // If O won print this
        else if (checkMainWinner(playerO)) {
            System.out.println("Congratulations, Player O won!");
        }

    }
    
    // This method simulates each turn in the game
    private int[] itsMyTurn(int[] moveBounds, boolean xTurn) {
        
        // There are move bounds but the board is full
        if (moveBounds[2] != -1 && moveBounds[3] != -1 && isFull(moveBounds[2], moveBounds[3])) {
            int[] moves = {-1,-1,-1,-1}; // Remove restrictions
            moveBounds = moves;
        }
        
        // To hold current player's move information
        int[] previousMove = new int[4];
        
        // X goes
        if (xTurn) {
            System.out.println("It's X's turn, make a move in " + boardName(moveBounds[2], moveBounds[3]) + "\n\n");
            previousMove = playerX.doMove(moveBounds);
        }
        
        // O goes
        else {
            System.out.println("It's O's turn, make a move in " + boardName(moveBounds[2], moveBounds[3]) + "\n\n");
            previousMove = playerO.doMove(moveBounds);
        }
        return previousMove; // Return the new restrictions with the player's most recent move
    }

}