package com.example.nick.checkers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nick on 11/13/2016.
 */
public class BoardFragment extends Fragment implements View.OnTouchListener {
    private BoardTableView boardView;

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

        int squareSize = 100;//(int) (TableConfig.convertDpToPixel(TableConfig.DEFAULT_PIN_SIZE, getActivity()));

        int[] values = boardView.makeBoard(8, 8, squareSize);
    }

    public boolean onTouch(View view, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        //implement stuff

        return false;
    }
}
