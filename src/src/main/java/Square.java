/**
 * Created by Nick on 11/10/2016.
 */
public class Square {
    //private String name; //might not need a name field at all; can be calculated in-method from xPosition and yPosition
    private boolean color; //true = red, false = black
    private Piece occupant;
    private int xPosition;
    private int yPosition;
    private Board board;

    public Square(boolean color, int xPosition, int yPosition, Board board) {
        //this.name = name;
        this.color = color;
        //this.occupant = occupant; //don't set in constructor, set in board's constructor
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.board = board;
    }

    public Piece getOccupant() {
        return this.occupant;
    }

    public void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public boolean isRed() {
        return color;
    }

    public boolean isBlack() {
        return !color;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public Board getBoard() {
        return this.board;
    }
}
