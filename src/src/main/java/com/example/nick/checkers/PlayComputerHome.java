package com.example.nick.checkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayComputerHome extends AppCompatActivity { //might want to have it extend playFriendHome
    protected boolean gameOver;
    protected boolean playerTurn;
    private BoardFragment game;
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_computer_home);
        this.game = (BoardFragment) getFragmentManager().findFragmentById(R.id.checkerBoard);
        playerTurn = true; //true = player1, false = player2
        this.difficulty = 0;
        //playGame();
    }

    public void playGame() {
        gameOver = false;
        //Board board = (R.layout.activity_play_computer_home);

        while(!gameOver) {
            if(playerTurn) {
                //wait
            } else { //computer's turn
                getAIMove();
            }
        }

       if (askForPlayAgain()) {
           reset();
       }
    }

    public void flipTurn() {
        playerTurn = !playerTurn;
    }

    public boolean askForPlayAgain() {
        return false;
    }

    public void returnToMain(View v){ finish(); }

    private void getAIMove() {
        if(this.difficulty == 0) {
            getEasyAIMove();
        } else {
            getHardAIMove();
        }
    }

    private void getEasyAIMove() {
        Square[] move = new Square[2];
    }

    private void getHardAIMove() {
        Square[] move = new Square[2];
    }

    private void reset() {

    }

}
