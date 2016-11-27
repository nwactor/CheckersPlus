package com.example.nick.checkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayComputerHome extends AppCompatActivity { //might want to have it extend playFriendHome
    boolean computerGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_computer_home);

        //playGame();
    }

    public void playGame() {
        computerGameOver = false;
        //Board board = (R.layout.activity_play_computer_home);

        while(!computerGameOver) {

        }


    }

    public boolean askForPlayAgain() {
        return false;
    }

    public void returnToMain(View v){ finish(); }

}
