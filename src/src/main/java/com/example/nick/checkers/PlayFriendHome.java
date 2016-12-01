package com.example.nick.checkers;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayFriendHome extends FragmentActivity {
    protected boolean gameOver;
    protected boolean currentTurn;
    private BoardFragment game;
    private boolean winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_friend_home);
        this.game = (BoardFragment) getFragmentManager().findFragmentById(R.id.checkerBoard);
        gameOver = false;
        currentTurn = true; //true = player1, false = player2
    }

    public void flipTurn() {
        currentTurn = !currentTurn;
        TextView turnInfo = (TextView) findViewById(R.id.currentTurnFriend);
        if(currentTurn) {
            turnInfo.setText("Player 1's Turn");
        } else {
            turnInfo.setText("Player 2's Turn");
        }
    }

    public void checkWin(Board board) {
        if(board.p2Remaining() == 0) {
            this.winner = this.currentTurn;
            showWinBanner();
            this.gameOver = true;
        } else if(board.p1Remaining() == 0) {
            this.winner = this.currentTurn;
            showWinBanner();
            this.gameOver = true;
        }

        //TODO: implement the win if opponent has no moves rule
    }

    /*The method that decides which toast message is displayed:
       The base index is used to indicate the type of event trigger calling makeToast
       The string array in the res/strings has an index range dedicated to each event type
       Each event type has a chance of triggering a Toast flavor text
     */
    public void makeToast(int baseIndex){
        //This if statement gives the base probability of a message triggering
        if(Math.random()<= 1) {
            Resources res = getResources();
            String[] flavors = res.getStringArray(R.array.flavor_toasts);
            //These if statements limit the range of messages that trigger when called by a certain event
            if(baseIndex==1) {
                Random rn = new Random();
                int index = rn.nextInt(8 - 0 + 1) + 0;

                Toast toast = Toast.makeText(getApplicationContext(), flavors[index], Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            } else if (baseIndex==2) {
                Random rn = new Random();
                int index = rn.nextInt(9);

                Toast toast = Toast.makeText(getApplicationContext(), flavors[index], Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        }
    }

    public void showWinBanner() {
        TextView banner = (TextView) findViewById(R.id.victoryBanner);
        if(this.winner) { //is player1
            banner.setText(R.string.p1_victory);
        } else {
            banner.setText(R.string.p2_victory);
        }
        banner.setVisibility(View.VISIBLE);
    }

    public void hideWinBanner() {
        TextView banner = (TextView) findViewById(R.id.victoryBanner);
        banner.setVisibility(View.INVISIBLE);
    }

    public void resetGame() {
        //reset banner
        hideWinBanner();

        //reset board
        game.getBoardView().getBoard().resetBoard();

        //reset turn
        currentTurn = true; //true = player1, false = player2
        TextView turnInfo = (TextView) findViewById(R.id.currentTurnFriend);
        turnInfo.setText("Player 1's Turn");

        //start game
        this.gameOver = false;
    }

    public void onNewGameClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("New Game")
                .setMessage("Are you sure you want to start a new game?")
                .setIcon(R.drawable.app_icon)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        PlayFriendHome.this.resetGame();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void onResignClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Resign")
                .setMessage("Do you really want to resign?")
                .setIcon(R.drawable.app_icon)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        PlayFriendHome.this.winner = !PlayFriendHome.this.currentTurn;
                        PlayFriendHome.this.showWinBanner();
                        PlayFriendHome.this.gameOver = true;
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void returnToMain(View v){ finish(); }

}
