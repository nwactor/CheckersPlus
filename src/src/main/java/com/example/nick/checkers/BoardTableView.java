package com.example.nick.checkers;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Nick on 11/13/2016.
 */
public class BoardTableView extends ViewGroup {
    private int squareSize;
    private Board board;

    public BoardTableView(Context context) {
        super(context);
    }

    public int[] makeBoard(int width, int height, int squareSize) {
        this.squareSize = squareSize;

        int rows = height / squareSize + 1;
        int columns =  width / squareSize + 1;

        this.board = new Board (rows, columns);
        Square[][] squares = new Square[rows][columns];

        //apparently only half of the square are used, so might want to remove those
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean redOrBlack = true;
                if (i % 2 == 0) {
                    if (j % 2 == 0) { // first case where square should be black
                        redOrBlack = false;
                    }
                } else {
                    if (j % 2 != 0) { // second case where square should be black
                        redOrBlack = false;
                    }
                }
                squares[i][j] = new Square(getContext(), redOrBlack, j + 1, i + 1, board);
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
}
