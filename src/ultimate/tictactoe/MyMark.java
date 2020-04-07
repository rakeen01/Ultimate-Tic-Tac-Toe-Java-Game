package ultimate.tictactoe;

/* Analysis:
    The game must have each of its players choose their own mark (i.e. 'X' or 'O').
    This class represents a mark.
*/

/* Design:
    Create a String variable which will hold the mark information,
    getters and setters.
*/

public class MyMark {

    private String mark;

    // Constructor sets a mark for the player
    public MyMark(String userMark) {
        setMark(userMark);
    }
    
    // Getter
    public String getMark() {
        return mark;
    }
    
    // Setter
    private void setMark(String userMark) {
        mark = userMark;
    }
}
