package com.example.nick.checkers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayFriendHome extends FragmentActivity {
    protected boolean friendGameOver;
    protected boolean currentTurn;
    private BoardFragment game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_friend_home);
        this.game = (BoardFragment) getFragmentManager().findFragmentById(R.id.checkerBoard);

        //playGame();
    }

    public void playGame() {
        //Set up for game
        friendGameOver = false;
        Board gameBoard = game.getBoardView().getBoard();

        currentTurn = true; //true = player1, false = player2


        //main game loop!
        while(!friendGameOver) {
            //move
            //waiting for onTouch


            //check win condition
            if(checkWin(gameBoard, currentTurn)) {
                //TODO: implement end game pop up
                break;
            } else {
                //switch turn
                currentTurn = !currentTurn;
            }
        }


    }

    private boolean checkWin(Board board, boolean currentTurn) {
        if(currentTurn) { //pl can only win on their turn
            if(board.p2Remaining() == 0) {
                return true;
            }
        } else { //p2 can only win on their turn
            if(board.p1Remaining() == 0) {
                return true;
            }
        }

        //TODO: implement the win if opponent has no moves rule
        return false;
    }

    public boolean askForPlayAgain() {
        return false;
    }

    public void returnToMain(View v){ finish(); }
}
