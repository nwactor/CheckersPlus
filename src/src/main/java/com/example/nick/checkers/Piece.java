package com.example.nick.checkers;

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
}
