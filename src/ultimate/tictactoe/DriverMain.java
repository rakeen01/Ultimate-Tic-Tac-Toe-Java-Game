package ultimate.tictactoe;
import java.util.Scanner;

/* 
    Author: Md Rakeen Murtaza
    CS 2336.006
    The following program was completed on 1st December 2019.

*/

/* Analysis:

    The following program implements a fully-functioning game of the traditional
    'Ultimate Tic-Tac-Toe'. The basic rules are:

        1. The user must indicate their letter of choice, and whether they want to play
           against the computer or another human player at the beginning of each game.

        2.  Each turn, the user marks one of the small squares. So when the user
            gets three in a row on a small board, the user wins that board.

        3.  To win the game, the user needs to win three small boards in a row.

        4.  The user can only place a mark on the board determined by the position of the user's
            opponent’s last placed mark. So, if they put an X or O in the top right corner
            of a square, the user's next move must occur in the top right board.
 
    
    A few specific rules apply to this version of the game:
        
        1.  A move can take place as long as there is an open square on a board. This means
            that the user/computer can force the opponent to choose an open square in an already
            won/tied board.

        2.  If the board is already full, the user gets a free pass to place his/her mark in
            any other board.

*/

/* Design:
    
    The entities involved in the implementation of this game has been divided so that they
    can be represented by each of the classes. The classes are:
    1.  DriverMain: This is the driver program which runs the game
    2.  MyMark:   This class represents the symbol chosen by each player
    3.  Computer:   This class represents information and functionalities of the computer playing the game.
    4.  Human:      This class represents information and functionalities of the human playing the game.
    5.  SmallBoard: This class represents the smaller tic-tac-toe boards.
    6.  LargeBoard: This class represents the ultimate TTT board.
    7.  MyGame:     Called by DriverMain, this class utilizes the other classes to start the game, run
                    the game, print the output after each game and check for the winner for each board and the
                    overall game.
    8.  Player: An abstract class extended by Human and Computer


*/

/* Test:
    1. Check if winner of a board changes = does not
    2. Check Computer vs Computer = works
    3. Check Human vs Computer = works
    4. Check Human vs Human = works
    5. Put invalid input = input validation works successfully
    6. Try selecting a different board = does not allow 
    7. Try putting mark on a non-empty index = does not allow
    8. Player is allowed a free pass when board is full = works
*/

public class DriverMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Set the two player objects to null
        Player x = null;
        Player o = null;

        // Create a new ultimate TTT board
        LargeBoard ultimateBoard = new LargeBoard();

        // Welcome message
        System.out.println("===== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
                
        // Get user's choice of game type
        System.out.println("Do you want to play against the computer or a friend?");
        System.out.println("Type 1 to take on the computer and type 2 to play against a human player. (Hint: Type 3 and watch the computer go berserk!): ");
        int num = input.nextInt();

        // Input validation
        while (num != 1 && num != 2 && num != 3){
            System.out.println("Woops! You've entered an invalid input! Try again.");
            System.out.println("Type 1 to take on the computer and type 2 to play against a human player. (Hint: Type 3 and watch the computer go berserk!): ");
            num = input.nextInt();
        }
        
        char userMark  = 'a';
        
        // The user wants to participate
        if(num != 3){
            
            // Prompt user for mark input
            System.out.println("Awesome! Choose your mark! Type 'X' to go first or type 'O' to go second: ");
            userMark = input.next().charAt(0);

            // Input validation
            while (userMark != 'x' && userMark != 'X' && userMark != 'o' && userMark != 'O') {
                System.out.println("Woops! You've entered an invalid input! Try again.");
                System.out.println("Choose your mark! Type 'X' to go first or type 'O' to go second: ");
                userMark = input.next().charAt(0);
            }
        }

        // If the user chose 'O' and wants a computer to be 'X'
        if (num == 1 && (userMark == 'o' || userMark == 'O')) {
            x = new Computer(new MyMark("X"), ultimateBoard);
            o = new Human(new MyMark("O"), ultimateBoard);
        }

        // If the user chose 'O' and wants a human to be 'X'
        else if (num == 2) {
            x = new Human(new MyMark("X"), ultimateBoard);
            o = new Human(new MyMark("O"), ultimateBoard);
        }

        // If the user chose 'X' and wants a computer to be 'O'
        else if (num == 1 && (userMark == 'x' || userMark == 'X')) {
            x = new Human(new MyMark("X"), ultimateBoard);
            o = new Computer(new MyMark("O"), ultimateBoard);   
        } 
        
        // User wants to watch the computer play against itself
        else if(num == 3){
            x = new Computer(new MyMark("X"), ultimateBoard);
            o = new Computer(new MyMark("O"), ultimateBoard);
        }

        // Create a MyGame object
        MyGame newGame = new MyGame(x, o, ultimateBoard);

        // Run the game
        newGame.run();

    }
}
