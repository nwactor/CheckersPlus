package com.example.nick.checkers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nick on 11/13/2016.
 */
public class BoardFragment extends Fragment implements View.OnTouchListener {
    private BoardTableView boardView;
    private Square lastTouched;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.board_fragment, container, true);
        this.boardView = (BoardTableView) view.findViewById(R.id.boardTable);
        this.boardView.setOnTouchListener(this);
        createBoard();

        //does what?
        this.setRetainInstance(true);
        return view;
    }

    private void createBoard() {
        //Display d = getActivity().getWindowManager().getDefaultDisplay();

        //need to do math in order to calculate the perfect square size
        int squareSize = 100;//(int) (TableConfig.convertDpToPixel(TableConfig.DEFAULT_PIN_SIZE, getActivity()));

        int[] values = boardView.makeBoard(8, 8, squareSize);
    }

    public BoardTableView getBoardView() {
        return this.boardView;
    }

    public boolean onTouch(View view, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        //Need to somehow set the fragment size to only the visible board size,
        //in order to get correct x and y
        /*
        //get the square that was touched
        Board board = this.boardView.getBoard();
        Square current = board.getSquare(x, y);
        */

        //don't do anything if the square has no piece or does not belong to the turn's owner

        //if lastTouched = null, light up available movement squares, and save this square as the last touched one

        //if lastTouched != null, if this is an available movement square, call move(lastTouched, this)
            //then set lastTouched to null

        return false;
    }
}
