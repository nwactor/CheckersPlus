package com.example.nick.checkers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.nick.checkers.R;

/**
 * Created by Nick on 11/10/2016.
 * Class to hold information about all of the squares on the checkerboard.
 */
public class Board {
    private int rows;
    private int columns;
    private Square[][] squares;

    public Board(int rows, int columns) {
        /*
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.com.example.nick.checkers.Board,
                0, 0);

        try {
            mRows = a.getInteger(R.styleable.Board_rows, 16);
            mColumns = a.getInteger(R.styleable.Board_columns, 16);
        } finally {
            a.recycle();
        }
        */
        this.rows = rows;
        this.columns = columns;
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

        //place black pieces for game start
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    squares[i - 1][j - 1].setOccupant(new Piece(false, false, squares[i - 1][j - 1]));
                }
            }
        }
        //place red pieces for game start
        for(int i = rows; i > rows - 3; i--) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    squares[i - 1][j - 1].setOccupant(new Piece(true, false, squares[i - 1][j - 1]));
                }
            }
        }
    }

    public Square getSquare(int xPosition, int yPosition) {
        return this.squares[xPosition - 1][yPosition - 1];
    }

}
