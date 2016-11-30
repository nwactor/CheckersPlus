package com.example.nick.checkers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.jar.Attributes;

/**
 * Created by Nick on 11/13/2016.
 */
public class BoardTableView extends ViewGroup {
    private int squareSize;
    private int rows;
    private int columns;
    private Board board;
    private Context context;

    public BoardTableView(Context context) {
        super(context);
        this.context = context;
    }

    public BoardTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BoardTableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public int[] makeBoard(int rows, int columns, int squareSize) {
        this.squareSize = squareSize;
        this.rows = rows;
        this.columns = columns;

        //int rows = height / squareSize + 1;
        //int columns =  width / squareSize + 1;

        this.board = new Board (rows, columns);
        Square[][] squares = new Square[rows][columns];

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean redOrBlack = false;
                if (i % 2 == 0) {
                    if (j % 2 == 0) { // first case where square should be red
                        redOrBlack = true;
                    }
                } else {
                    if (j % 2 != 0) { // second case where square should be red
                        redOrBlack = true;
                    }
                }
                squares[i][j] = new Square(getContext(), redOrBlack, j, i, board);
                this.addView(squares[i][j]);
            }
        }

        //put the pieces on the board, given all the squares
        board.setBoard(squares);

        return new int[]{rows , columns};
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int numberOfSquares = getChildCount();

        for(int i = 0; i < numberOfSquares; i++) {
            Square square = (Square) getChildAt(i);

            //or the getRow and getCol methods from example?
            int left = square.getXPosition() * squareSize;
            int top = square.getYPosition() * squareSize;
            int right = left + squareSize;
            int bottom = top + squareSize;

            square.layout(left, top, right, bottom);
        }
    }

    public Board getBoard() {
        return this.board;
    }

}
