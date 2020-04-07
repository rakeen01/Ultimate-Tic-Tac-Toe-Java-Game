package ultimate.tictactoe;
import java.util.Iterator;

/* Analysis:
    This is class represents the ultimate TTT board
*/

/* Design:
    The constructor initializes a 3x3 array where the elements are SmallBoard objects.
    Set getters and setters for the large board information. Convert row-wise
    information into a String format so that it can easily be printed. Override
    the iterator methods next() and hasNext().
*/

public class LargeBoard implements Iterable {

    private SmallBoard[][] ultimateBoard;

    // The LargeBoard constructor initializes a 3x3 array
    // where the elements are SmallBoard objects
    public LargeBoard() {
        ultimateBoard = new SmallBoard[3][3];
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                ultimateBoard[row][col] = new SmallBoard();
            }
        }
    }

    // Get the mark present in a specific location on the board
    public MyMark getMarkFrom(int largeRow, int largeCol, int smallRow, int smallCol) {
        return ultimateBoard[largeRow][largeCol].getMarkFrom(smallRow, smallCol);
    }

    // Set the mark at a specific location on the board, using the small board setter method
    public void setMarkAt(int largeRow, int largeCol, int smallRow, int smallCol, MyMark userMark) {
        ultimateBoard[largeRow][largeCol].setMarkAt(smallRow, smallCol, userMark);
    }

    // Get a specific small board
    public SmallBoard getSmallBoard(int row, int col) {
        return ultimateBoard[row][col];
    }
    
    // Return the entire ultimate TTT board row line in String format
    private String printLine(int row) {
        int R = row/3;
        return getSmallBoard(R, 0).printRow(row-R*3) + " | " + getSmallBoard(R, 1).printRow(row-R*3) + " | " + getSmallBoard(R, 2).printRow(row-R*3) + "\n";
    }

    // Store the board information in a string format
    public String storeBoard() {
        int i = 0;
        String s = "    0 1 2   0 1 2   0 1 2\n\n";
        for (int r = 0; r < 3; r++) {
            s += r + "   " + printLine(r);
        }
        s += "    ---------------------\n";
        s += "Board 1       2       3  \n";
        s += "    ---------------------\n";
        for (int r = 3; r < 6; r++) {
            s += i + "   " + printLine(r);
            i++;
        }
        s += "    ---------------------\n";
        s += "Board 4       5       6  \n";
        s += "    ---------------------\n";
        i = 0;
        for (int r = 6; r < 9; r++) {
            s += i + "   " + printLine(r);
            i++;
        }
        s += "    ---------------------\n";
        s += "Board 7       8       9  \n";
        s += "    ---------------------\n";
        s += "\n";
        return s;
    }

    // Override next and hasNext
    // to use iterator functionalities
    @Override
    public Iterator<SmallBoard> iterator() {
        Iterator<SmallBoard> look = new Iterator<SmallBoard>() {

            private int currentIndex = 0;

            // To move along the rows of the ultimate TTT board by moving to
            // the next small board
            @Override
            public SmallBoard next() {
                return ultimateBoard[currentIndex / 3][currentIndex++ % 3];
            }
            
            // Check if another small board exists
            @Override
            public boolean hasNext() {
                return currentIndex < 9;
            }

        };
        return look;
    }

}
