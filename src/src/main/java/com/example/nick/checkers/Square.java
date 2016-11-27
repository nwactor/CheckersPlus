package com.example.nick.checkers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.example.nick.checkers.Board;
import com.example.nick.checkers.Piece;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Nick on 11/10/2016.
 */
public class Square extends ImageView {
    //private String name; //might not need a name field at all; can be calculated in-method from xPosition and yPosition
    private boolean color; //true = red, false = black
    private Piece occupant;
    private Drawable display;
    private int xPosition;
    private int yPosition;
    private Board board;
    private Context context;

    public Square(Context context, boolean color, int xPosition, int yPosition, Board board) {
        super(context);

        this.context = context;
        //this.name = name;
        this.color = color;
        //this.occupant = occupant; //don't set in constructor, set in board's constructor
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.board = board;

        //Load the image
        if(color) { //red square
            this.display = getResources().getDrawable(R.drawable.square_red);
        } else { //black square
            this.display = getResources().getDrawable(R.drawable.square_black);
        }
        setImageDrawable(display);
        //id?
    }

    public Piece getOccupant() {
        return this.occupant;
    }

    public boolean hasOccupant() {
        return !(this.occupant == null);
    }

    public void setOccupant(Piece occupant) {
        this.occupant = occupant;

        if(occupant == null) {
            //only black squares will have their occupant set
            this.display = getResources().getDrawable(R.drawable.square_black);
            return;
        }

        //update graphic
        if(occupant.isP1()) {
            //there should be a global variable that stores the chosen p1 color
            if(occupant.isKing()) {
                this.display = getResources().getDrawable(R.drawable.blue_king);
                setImageDrawable(this.display);
            } else {
                this.display = getResources().getDrawable(R.drawable.blue_piece);
                setImageDrawable(this.display);
            }
        } else { //belongs to player 2
            if(occupant.isKing()) {
                this.display = getResources().getDrawable(R.drawable.green_king);
                setImageDrawable(this.display);
            } else {
                this.display = getResources().getDrawable(R.drawable.green_piece);
                setImageDrawable(this.display);
            }
        }
    }

    public void removeOccupant() {
        this.occupant = null;

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

    public Square getUpLeft() {
        return board.getSquare(this.xPosition - 1, this.yPosition + 1);
    }

    public Square getUpRight() {
        return board.getSquare(this.xPosition + 1, this.yPosition + 1);
    }

    public Square getDownLeft() {
        return board.getSquare(this.xPosition - 1, this.yPosition - 1);
    }

    public Square getDownRight() {
        return board.getSquare(this.xPosition + 1, this.yPosition - 1);
    }

    //returns false if the move is invalid
    public boolean move(Square destination) {
        if(!this.getAvailableMoves().contains(destination)) {
            return false;
        } else {
            //move the piece
            destination.setOccupant(this.getOccupant());
            this.setOccupant(null);

            //remove taken pieces
            int x = destination.getXPosition();
            int y = destination.getYPosition();
            Square current = this;

            if(this.xPosition > x && this.yPosition > y) { //down left jump
                while(current.getXPosition() != x && current.getYPosition() != y) {
                    current = current.getDownLeft();
                    current.setOccupant(null);
                }
            } else if(this.xPosition < x && this.yPosition > y) { //down right jump
                while(current.getXPosition() != x && current.getYPosition() != y) {
                    current = current.getDownRight();
                    current.setOccupant(null);
                }
            } else if(this.xPosition < x && this.yPosition < y) { //up right jump
                while (current.getXPosition() != x && current.getYPosition() != y) {
                    current = current.getUpRight();
                    current.setOccupant(null);
                }
            } else if(this.xPosition > x && this.yPosition < y) { //up left jump
                while (current.getXPosition() != x && current.getYPosition() != y) {
                    current = current.getUpLeft();
                    current.setOccupant(null);
                }
            } else {
                System.out.println("Something is very wrong.");
            }
            return true;
        }
    }

    //method for getting all the available moves for the square's piece
    private HashSet<Square> getAvailableMoves() {
        if(!this.hasOccupant()) { return null; }

        Piece p = this.getOccupant();

        HashSet<Square> available = new HashSet<Square>();

        if(p.isP1()) {
            for(Square square : this.getUpwardAvailable(p.getTeam())) {
                available.add(square);
            }
            if(p.isKing()) {
                for(Square square : this.getDownwardAvailable(p.getTeam())) {
                    available.add(square);
                }
            }
        } else {
            for(Square square : this.getDownwardAvailable(p.getTeam())) {
                available.add(square);
            }
            if(p.isKing()) {
                for(Square square : this.getUpwardAvailable(p.getTeam())) {
                    available.add(square);
                }
            }
        }

        return available;
    }


    private LinkedList<Square> getUpwardAvailable(boolean currentTeam) {
        LinkedList<Square> upwardAvailable = new LinkedList<Square>();

        Square upLeft = this.getUpLeft();
        Square upRight = this.getUpRight();

        if(upLeft != null) {
            if (upLeft.hasOccupant()) {
                if (upLeft.getOccupant().getTeam() != currentTeam) {
                    //check for a possible jump to the left
                    if (upLeft.getUpLeft() != null && !upLeft.getUpLeft().hasOccupant()) {
                        for(Square square : upLeft.getUpLeft().getUpwardAvailable(currentTeam)) {
                            upwardAvailable.add(square);
                        }
                    }
                }
            } else {
                upwardAvailable.add(upLeft);
            }
        }
        if(upRight != null) {
            if(upRight.hasOccupant()) {
                if(upRight.getOccupant().getTeam() != currentTeam) {
                    //check for a possible jump to the right
                    if(upRight.getUpRight() != null && !upRight.getUpLeft().hasOccupant()) {
                        for(Square square : upLeft.getUpRight().getUpwardAvailable(currentTeam)) {
                            upwardAvailable.add(square);
                        }
                    }
                }
            } else {
                upwardAvailable.add(upRight);
            }
        }

        return upwardAvailable;
    }


    private LinkedList<Square> getDownwardAvailable(boolean currentTeam) {
        LinkedList<Square> downwardAvailable = new LinkedList<Square>();

        Square downLeft = this.getDownLeft();
        Square downRight = this.getDownRight();

        if(downLeft != null) {
            if (downLeft.hasOccupant()) {
                if (downLeft.getOccupant().getTeam() != currentTeam) {
                    //check for a possible jump to the left
                    if (downLeft.getDownLeft() != null && !downLeft.getDownLeft().hasOccupant()) {
                        for(Square square : downLeft.getDownLeft().getAvailableMoves()) {
                            downwardAvailable.add(square);
                        }
                    }
                }
            } else {
                downwardAvailable.add(downLeft);
            }
        }
        if(downRight != null) {
            if(downRight.hasOccupant()) {
                if(downRight.getOccupant().getTeam() != currentTeam) {
                    //check for a possible jump to the right
                    if(downRight.getDownRight() != null && !downRight.getDownLeft().hasOccupant()) {
                        for(Square square : downLeft.getDownRight().getAvailableMoves()) {
                            downwardAvailable.add(square);
                        }
                    }
                }
            } else {
                downwardAvailable.add(downRight);
            }
        }

        return downwardAvailable;
    }

}
