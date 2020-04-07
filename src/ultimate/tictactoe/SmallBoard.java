package ultimate.tictactoe;

/* Analysis:
    This is a class which represent each of the small boards of a normal tic-tac-toe
    game, embedded inside each of the large board boxes.
*/

/* Design:
    Set getters and setters for the small board information
*/

public class SmallBoard {
    
    private String winner = "-"; // - means no winner yet, F means tie, X means X won, and O means O won
    private MyMark[][] normalBoard;
    
    // Constructor initializes the 3x3 small board
    public SmallBoard() {
        normalBoard = new MyMark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                normalBoard[i][j] = new MyMark("-");
            }
        }
    }
    
    // Getter for the small board winner
    public String getWinner() {
        return winner;
    }

    // Setter for the small board winner
    public void setWinner(String thisWinner) {
        winner = thisWinner;
    }
    
    // Getter for mark at a specific index
    public MyMark getMarkFrom(int row, int column) {
        return normalBoard[row][column];
    }

    // Setter for mark at a specific index
    public void setMarkAt(int row, int column, MyMark userMark) {
        normalBoard[row][column] = userMark;
    }

    // Return row in String format
    public String printRow(int row) {
        return normalBoard[row][0].getMark() + " " + normalBoard[row][1].getMark() + " " + normalBoard[row][2].getMark();
    }

}
