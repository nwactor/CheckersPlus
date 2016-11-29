package com.example.nick.checkers;

import java.util.HashSet;

/**
 * Created by Nick on 11/10/2016.
 */
public class Piece {
    private boolean team; //true = player1, false = player2
    private boolean isKing;
    //private Square position;

    //possibly change name to Checker
    public Piece(boolean team, boolean isKing) {
        this.team = team;
        this.isKing = isKing;
        //this.position = position;
    }

    public boolean isP1() {
        return this.team;
    }

    public boolean isP2() {
        return !this.team;
    }

    //true = p1, false = p2
    public boolean getTeam() {
        return this.team;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void makeKing() {
        this.isKing = true;
    }

    //Following methods moved to square class
    /*

    public HashSet<Square> getAvailableMoves() {
        HashSet<Square> available = new HashSet<Square>();
        Square left = this.getAdjacentForward()[0];
        Square right = this.getAdjacentForward()[1];

        if(this.isKing) {

        } else {
                if(left != null) {
                    if (left.hasOccupant()) {
                        if (left.getOccupant().getTeam() != this.getTeam()) {
                            //check for a possible jump to the left
                            if (left.getOccupant().getForwardLeft() != null && !left.getOccupant().getForwardLeft().hasOccupant()) {
                                available.add(left.getOccupant().getForwardLeft());
                                //TODO: change to square and add recursion
                            }
                        }
                    } else {
                        available.add(left);
                    }
                }
                if(right != null) {
                    if(right.hasOccupant()) {
                        if(right.getOccupant().getTeam() != this.getTeam()) {
                            //check for a possible jump to the right
                            if(right.getOccupant().getForwardRight() != null && !right.getOccupant().getForwardRight().hasOccupant()) {
                                available.add(right.getOccupant().getForwardRight());
                                //TODO: change to square and add recursion
                            }
                        }
                    } else {
                        available.add(right);
                    }
                }
        }

        return available;
    }

    private Square getForwardLeft() {
        return this.getAdjacentForward()[0];
    }

    private Square getForwardRight() {
        return this.getAdjacentForward()[1];
    }

    //Gets the two forward squares for the piece
    private Square[] getAdjacentForward() {
        int boardRows = this.position.getBoard().getRows();
        int boardColumns = this.position.getBoard().getColumns();
        Square[] adjacentForward = new Square[2];

        if(this.isP1()) { //belongs to player 1
            if(this.position.getYPosition() == 0) { //the checker has reached the end of the board
                return null;
            } else {
                if(this.position.getXPosition() == 0){ //left edge of the board
                    adjacentForward[0] = null;
                } else { //forward to left
                    adjacentForward[0] = this.position.getBoard().getSquare(this.position.getXPosition() - 1 - 1, this.position.getYPosition() - 1 - 1);
                }
                if(this.position.getXPosition() == boardColumns - 1) { //right edge of the board
                    adjacentForward[1] = null;
                } else { //forward to right
                    adjacentForward[1] = this.position.getBoard().getSquare(this.position.getXPosition() + 1, this.position.getYPosition() - 1);
                }
            }
        } else { //this.isP2()
            if(this.position.getYPosition() == boardRows) { //the checker has reached the end of the board
                return null;
            } else {
                if(this.position.getXPosition() == 0){ //left edge of the board
                    adjacentForward[0] = null;
                } else { //forward to left
                    adjacentForward[0] = this.position.getBoard().getSquare(this.position.getXPosition() - 1, this.position.getYPosition() - 1);
                }
                if(this.position.getXPosition() == boardColumns - 1) { //right edge of the board
                    adjacentForward[1] = null;
                } else { //forward to right
                    adjacentForward[1] = this.position.getBoard().getSquare(this.position.getXPosition() + 1, this.position.getYPosition() - 1);
                }
            }
        }

        return adjacentForward;
    }

    //TODO: implement
    //For king moves
    private Square[] getAdjacentAll() {
        return null;
    }  */
}
