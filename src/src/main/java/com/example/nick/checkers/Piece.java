package com.example.nick.checkers;

/**
 * Created by Nick on 11/10/2016.
 */
public class Piece {
    private boolean team; //true = player1, false = player2
    private boolean isKing;
    private Square position;

    //possibly change name to Checker
    public Piece(boolean team, boolean isKing, Square position) {
        this.team = team;
        this.isKing = isKing;
        this.position = position;
    }

    public boolean isP1() {
        return this.team;
    }

    public boolean isP2() {
        return !this.team;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public Square[] getAdjacentForward() {
        int boardRows = this.position.getBoard().getRows();
        int boardColumns = this.position.getBoard().getColumns();
        Square[] adjacentForward = new Square[2];

        if(this.isP1()) { //belongs to player 1
            if(this.position.getYPosition() == 1) { //the checker has reached the end of the board
                return null;
            } else {
                if(this.position.getXPosition() == 1){ //left edge of the board
                    adjacentForward[0] = null;
                } else {
                    adjacentForward[0] = this.position.getBoard().getSquare(this.position.getXPosition() - 1 - 1, this.position.getYPosition() - 1 - 1);
                }
                if(this.position.getXPosition() == boardColumns) { //right edge of the board
                    adjacentForward[1] = null;
                } else {
                    adjacentForward[1] = this.position.getBoard().getSquare(this.position.getXPosition() - 1 + 1, this.position.getYPosition() - 1 - 1);
                }
            }
        } else { //this.isP2()
            if(this.position.getYPosition() == boardRows) { //the checker has reached the end of the board
                return null;
            } else {
                if(this.position.getXPosition() == 1){ //left edge of the board
                    adjacentForward[0] = null;
                } else {
                    adjacentForward[0] = this.position.getBoard().getSquare(this.position.getXPosition() - 1 - 1, this.position.getYPosition() - 1 + 1);
                }
                if(this.position.getXPosition() == boardColumns) { //right edge of the board
                    adjacentForward[1] = null;
                } else {
                    adjacentForward[1] = this.position.getBoard().getSquare(this.position.getXPosition() - 1 + 1, this.position.getYPosition() - 1 + 1);
                }
            }
        }

        return adjacentForward;
    }

    //TODO: implement
    //For king moves
    public Square[] getAdjacentAll() {
        return null;
    }
}
