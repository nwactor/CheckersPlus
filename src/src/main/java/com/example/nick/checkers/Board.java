package com.example.nick.checkers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.nick.checkers.R;

import java.util.ArrayList;

/**
 * Created by Nick on 11/10/2016.
 * Class to hold information about all of the squares on the checkerboard.
 */
public class Board {
    private int rows;
    private int columns;
    private Square[][] squares;
    private ArrayList<Piece> p1Pieces;
    private ArrayList<Piece> p2Pieces;
    private Square lastTouched;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.lastTouched = null;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void setBoard(Square[][] squares) {
        //should throw error if size of square array doesn't match rows & columns

        this.squares = squares;
        this.p1Pieces = new ArrayList<Piece>();
        this.p2Pieces = new ArrayList<Piece>();

        //place p1 pieces for game start (bottom of board)
        for(int i = rows; i > rows - 3; i--) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    Piece piece = new Piece(true, false);
                    squares[i - 1][j - 1].setOccupant(new Piece(true, false));
                    p2Pieces.add(piece);
                }
            }
        }

        //place p2 pieces for game start (top of board)
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    Piece piece = new Piece(false, false);
                    squares[i - 1][j - 1].setOccupant(piece);
                    p1Pieces.add(piece);
                }
            }
        }

    }

    public Square getSquare(int xPosition, int yPosition) {
        if(xPosition < 0 || xPosition > columns || yPosition < 0 || yPosition > rows) {
            return null;
        } else {
            return this.squares[yPosition][xPosition];
        }
    }

    public Square getLastTouched() {
        return this.lastTouched;
    }

    public void setLastTouched(Square square) {
        this.lastTouched = square;
    }

    public int p1Remaining() {
        return this.p1Pieces.size();
    }

    public int p2Remaining() {
        return this.p2Pieces.size();
    }

}
