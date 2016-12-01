package com.example.nick.checkers;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
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
        this.color = color;
        //this.occupant = occupant; //set in board's constructor
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

        if(occupant == null) { System.out.println("A square is having its piece removed:");
            System.out.println(this.getXPosition() + ", " + this.getYPosition());
            //only black squares will have their occupant set
            this.display = getResources().getDrawable(R.drawable.square_black);
            setImageDrawable(display);
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

    //true = computer, false = friend
    public boolean getMode() {
        if(this.getContext() instanceof PlayComputerHome) {
            return true;
        } else {
            return false;
        }
    }

    //What happens when you touch a Square
    public boolean onTouchEvent(MotionEvent event) {

        //reaction for a game against the computer
        if(this.getMode()) {
            //do nothing if it's not the player's turn, or the game is over
            if(((PlayComputerHome) ((Activity) this.getContext())).playerTurn == false
                    || ((PlayComputerHome) ((Activity) this.getContext())).gameOver) {
                return true;
            }

            Square current = this;
            Square lastTouched = this.board.getLastTouched();

            System.out.println("Current: " + this.getXPosition() + ", " + this.getYPosition());
            if (lastTouched != null) {
                System.out.println("LastTouched: " + lastTouched.getXPosition() + ", " + lastTouched.getYPosition());
            } else {
                System.out.println("LastTouched: null");
            }

            if (lastTouched == null) {
                //don't do anything if the square has no piece or does not belong to the player
                if (!current.hasOccupant() || !this.occupant.getTeam()) {
                    return true;
                } else { //save this square as the last touched one, and highlight
                    this.board.setLastTouched(current);
                    //now should highlight
                }
            } else { //attempt to make a move from lastTouched to current
                if (lastTouched.move(current)) {
                    this.board.setLastTouched(null);
                    //if move is successful, change turn
                    ((PlayComputerHome) this.getContext()).flipTurn();
                } else {
                    //deselect the piece if another is selected
                    if (current.hasOccupant()) {
                        if (current.getOccupant().getTeam() == lastTouched.getOccupant().getTeam()) {
                            this.board.setLastTouched(current);
                        }
                    }
                }
            }
            return true;
        }

        //reaction for a game against a friend
        else {
            //do nothing if game over
            if (((PlayFriendHome) ((Activity) this.getContext())).gameOver) {
                return true;
            }

            boolean turn = ((PlayFriendHome) ((Activity) this.getContext())).currentTurn;
            Square current = this;
            Square lastTouched = this.board.getLastTouched();

            System.out.println("Current: " + this.getXPosition() + ", " + this.getYPosition());
            if (lastTouched != null) {
                System.out.println("LastTouched: " + lastTouched.getXPosition() + ", " + lastTouched.getYPosition());
            } else {
                System.out.println("LastTouched: null");
            }

            if (lastTouched == null) {
                //don't do anything if the square has no piece or does not belong to the turn's owner
                if (!current.hasOccupant() || this.occupant.getTeam() != turn) {
                    return true;
                } else { //save this square as the last touched one, and highlight
                    this.board.setLastTouched(current);
                    //now should highlight
                }
            } else { //attempt to make a move from lastTouched to current
                if (lastTouched.move(current)) {
                    //if move is successful check for game over then change turn
                    this.board.setLastTouched(null);
                    ((PlayFriendHome) this.getContext()).checkWin(board);
                    ((PlayFriendHome) this.getContext()).flipTurn();
                    System.out.println(this.board.p2Remaining());
                } else {
                    //deselect the piece if another is selected
                    if (current.hasOccupant()) {
                        if (current.getOccupant().getTeam() == lastTouched.getOccupant().getTeam()) {
                            this.board.setLastTouched(current);
                        }
                    }
                }
            }

            return true;
        }
    }

    /*Y-POSITION OF FOUR FOLLOWING METHODS IS COUNTER-INTUITIVE BECAUSE
    * THE UPPER LEFT HAND CORNER OF THE BOARD IS INDEX 0,0. SO, TO GET
    * THE SQUARE ABOVE YOU ON THE BOARD, YOU GO ONE Y-INDEX DOWN.*/

    public Square getUpLeft() {
        if(this.yPosition == 0 || this.xPosition == 0) {
            return null;
        }
        return board.getSquare(this.xPosition - 1, this.yPosition - 1);
    }

    public Square getUpRight() {
        if(this.yPosition == 0 || this.xPosition == board.getColumns() - 1) {
            return null;
        }
        return board.getSquare(this.xPosition + 1, this.yPosition - 1);
    }

    public Square getDownLeft() {
        if(this.yPosition == board.getRows() - 1 || this.xPosition == 0) {
            return null;
        }
        return board.getSquare(this.xPosition - 1, this.yPosition + 1);
    }

    public Square getDownRight() {
        if(this.yPosition == board.getRows() - 1 || this.xPosition == board.getColumns() - 1) {
            return null;
        }
        return board.getSquare(this.xPosition + 1, this.yPosition + 1);
    }


    //returns false if the move is invalid
    public boolean move(Square destination) {
        System.out.println("Available Moves:");
        for(Square square : this.getAvailableMoves()) {
            System.out.println(square.getXPosition() + ", " + square.getYPosition());
        }
        if(!this.getAvailableMoves().contains(destination)) {
            return false;
        } else {
            //set up
            int x = destination.getXPosition();
            int y = destination.getYPosition();
            Square start = this;

            //check for kinging
            if(start.getOccupant().isP1()) {
                if(y == 0) { start.getOccupant().makeKing(); }
            } else { //is p2
                if(y == this.board.getRows() - 1) { start.getOccupant().makeKing(); }
            }

            //move the piece
            destination.setOccupant(this.getOccupant());
            this.setOccupant(null);

            //remove taken pieces
            if(this.xPosition > x && this.yPosition < y) { //down left jump
                start = start.getDownLeft();
                while(start.getXPosition() != x && start.getYPosition() != y) {
                    if(start.hasOccupant()) {
                        boolean team = start.getOccupant().getTeam();
                        start.setOccupant(null);
                        if(team) { this.board.decrementP1(); } else { this.board.decrementP2(); }
                    }
                    start = start.getDownLeft();
                }
            } else if(this.xPosition < x && this.yPosition < y) { //down right jump
                start = start.getDownRight();
                while(start.getXPosition() != x && start.getYPosition() != y) {
                    if(start.hasOccupant()) {
                        boolean team = start.getOccupant().getTeam();
                        start.setOccupant(null);
                        if(team) { this.board.decrementP1(); } else { this.board.decrementP2(); }
                    }
                    start = start.getDownRight();
                }
            } else if(this.xPosition < x && this.yPosition > y) { //up right jump
                start = start.getUpRight();
                while (start.getXPosition() != x && start.getYPosition() != y) {
                    if(start.hasOccupant()) {
                        boolean team = start.getOccupant().getTeam();
                        start.setOccupant(null);
                        if(team) { this.board.decrementP1(); } else { this.board.decrementP2(); }
                    }
                    start = start.getUpRight();
                }
            } else if(this.xPosition > x && this.yPosition > y) { //up left jump
                start = start.getUpLeft();
                while (start.getXPosition() != x && start.getYPosition() != y) {
                    if(start.hasOccupant()) {
                        boolean team = start.getOccupant().getTeam();
                        start.setOccupant(null);
                        if(team) { this.board.decrementP1(); } else { this.board.decrementP2(); }
                    }
                    start = start.getUpLeft();
                }
            } else {
                System.out.println("Something is very wrong.");
                System.out.println("start:" + start.getXPosition() + ", " + start.getYPosition());
                System.out.println("dest:" + destination.getXPosition() + ", " + destination.getYPosition());
            }

            return true;
        }
    }

    //method for getting all the available moves for the square's piece
    private HashSet<Square> getAvailableMoves() {

        //this should only ever get called if this has an occupant,
        //so no need to check for one
        Piece p = this.getOccupant();

        HashSet<Square> available = new HashSet<Square>();

        if(p.isP1()) {
            for(Square square : this.getUpwardAvailable(p.getTeam(), 0)) {
                available.add(square);
            }
            if(p.isKing()) {
                for(Square square : this.getDownwardAvailable(p.getTeam(), 0)) {
                    available.add(square);
                }
            }
        } else {
            for(Square square : this.getDownwardAvailable(p.getTeam(), 0)) {
                available.add(square);
            }
            if(p.isKing()) {
                for(Square square : this.getUpwardAvailable(p.getTeam(), 0)) {
                    available.add(square);
                }
            }
        }

        return available;
    }


    /*Next two are the helper methods for getAvailable
     * Bug that doesn't cause any problems: the left and right recursive calls can
      * add the opposite side to the available list, but this won't add anything that
      * wouldn't have been read anyway.*/

    //@param side tells the recursive calls which side to go down, so they don't go down both
    //0 = both, 1 = left, 2 = right
    private LinkedList<Square> getUpwardAvailable(boolean currentTeam, int side) {
        LinkedList<Square> upwardAvailable = new LinkedList<Square>();

        Square upLeft = this.getUpLeft();
        Square upRight = this.getUpRight();

        if(side == 0 || side == 1) {
            if (upLeft != null) {
                if (upLeft.hasOccupant()) {
                    if (upLeft.getOccupant().getTeam() != currentTeam) {
                        //recursively check for a possible jump to the left
                        if (upLeft.getUpLeft() != null && !upLeft.getUpLeft().hasOccupant()) {
                            //call method on square two steps away
                            LinkedList<Square> temp = upLeft.getUpLeft().getUpwardAvailable(currentTeam, 1);
                            if (temp.size() > 0) {
                                for (Square square : temp) {
                                    upwardAvailable.add(square);
                                }
                            }
                        } else { //base case: further jumps cannot be made (and the next square has a piece)
                            //if statement makes sure that this is not the original call
                            if (!this.hasOccupant()) {
                                upwardAvailable.add(this);
                            }
                        }
                    } else if(!this.hasOccupant()) { //one of the same team is past the jump
                        upwardAvailable.add(this);
                    }
                } else {
                    if(this.hasOccupant()) { //simplest case: upLeft exists and is empty
                        upwardAvailable.add(upLeft);
                    } else { //other base case: this is the last step of a jump (and the next square is empty)
                        upwardAvailable.add(this);
                    }
                }
            } else { //hit edge of board while jumping
                if (!this.hasOccupant()) {
                    upwardAvailable.add(this);
                }
            }
        } if(side == 0 || side == 2) {
            if (upRight != null) {
                if (upRight.hasOccupant()) {
                    if (upRight.getOccupant().getTeam() != currentTeam) {
                        //recursively check for a possible jump to the right
                        if (upRight.getUpRight() != null && !upRight.getUpRight().hasOccupant()) {
                            LinkedList<Square> temp = upRight.getUpRight().getUpwardAvailable(currentTeam, 2);
                            if (temp.size() > 0) {
                                for (Square square : temp) {
                                    upwardAvailable.add(square);
                                }
                            }
                        } else { //base case: further jumps cannot be made
                            //if statement makes sure that this is not the original call
                            if (!this.hasOccupant()) {
                                upwardAvailable.add(this);
                            }
                        }
                    } else if(!this.hasOccupant()) { //one of the same team is past the jump
                        upwardAvailable.add(this);
                    }
                } else {
                    if(this.hasOccupant()) { //simplest case: upRight exists and is empty
                        upwardAvailable.add(upRight);
                    } else { //other base case: this is the last step of a jump (and the next square is empty)
                        upwardAvailable.add(this);
                    }
                }
            } else { //hit edge of board while jumping
                if (!this.hasOccupant()) {
                    upwardAvailable.add(this);
                }
            }
        }
        return upwardAvailable;
    }


    //@param side tells the recursive calls which side to go down, so they don't go down both
    //0 = both, 1 = left, 2 = right
    private LinkedList<Square> getDownwardAvailable(boolean currentTeam, int side) {
        LinkedList<Square> downwardAvailable = new LinkedList<Square>();

        Square downLeft = this.getDownLeft();
        Square downRight = this.getDownRight();

        if(side == 0 || side == 1) {
            if (downLeft != null) {
                if (downLeft.hasOccupant()) {
                    if (downLeft.getOccupant().getTeam() != currentTeam) {
                        //recursively check for a possible jump to the left
                        if (downLeft.getDownLeft() != null && !downLeft.getDownLeft().hasOccupant()) {
                            LinkedList<Square> temp = downLeft.getDownLeft().getDownwardAvailable(currentTeam, 1);
                            if (temp.size() > 0) {
                                for (Square square : temp) {
                                    downwardAvailable.add(square);
                                }
                            }
                        } else { //base case: further jumps cannot be made
                            //if statement makes sure that this is not the original call
                            if (!this.hasOccupant()) {
                                downwardAvailable.add(this);
                            }
                        }
                    } else if(!this.hasOccupant()) { //one of the same team is past the jump
                        downwardAvailable.add(this);
                    }
                } else {
                    if(this.hasOccupant()) { //simplest case: downLeft exists and is empty
                        downwardAvailable.add(downLeft);
                    } else { //other base case: this is the last step of a jump (and the next square is empty)
                        downwardAvailable.add(this);
                    }
                }
            } else { //hit edge of board while jumping
                if (!this.hasOccupant()) {
                    downwardAvailable.add(this);
                }
            }
        }

        if(side == 0 || side == 2) {
            if (downRight != null) {
                if (downRight.hasOccupant()) {
                    if (downRight.getOccupant().getTeam() != currentTeam) {
                        //recursively check for a possible jump to the right
                        if (downRight.getDownRight() != null && !downRight.getDownRight().hasOccupant()) {
                            LinkedList<Square> temp = downRight.getDownRight().getDownwardAvailable(currentTeam, 2);
                            if (temp.size() > 0) {
                                for (Square square : temp) {
                                    downwardAvailable.add(square);
                                }
                            }
                        } else { //base case: further jumps cannot be made
                            //if statement makes sure that this is not the original call
                            if (!this.hasOccupant()) {
                                downwardAvailable.add(this);
                            }
                        }
                    } else if(!this.hasOccupant()) { //one of the same team is past the jump
                        downwardAvailable.add(this);
                    }
                } else {
                    if(this.hasOccupant()) { //simplest case: downRight exists and is empty
                        downwardAvailable.add(downRight);
                    } else { //other base case: this is the last step of a jump (and the next square is empty)
                        downwardAvailable.add(this);
                    }
                }
            } else { //hit edge of board while jumping
                if (!this.hasOccupant()) {
                    downwardAvailable.add(this);
                }
            }
        }

        return downwardAvailable;
    }

}
