package com.example.nick.checkers;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class PlayFriendHome extends FragmentActivity {
    protected boolean gameOver;
    protected boolean currentTurn;
    private BoardFragment game;
    private boolean winner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_friend_home);
        this.game = (BoardFragment) getFragmentManager().findFragmentById(R.id.checkerBoard);
        gameOver = false;
        currentTurn = true; //true = player1, false = player2
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

//The core method that desides what types of toats messages are displaye
    // The Base Index is used to indicate the type of event trigger is calling the makeToast method
    //The String array an xml has a index range dedicated to eac event type
    //Each Even t type has a chance of triggering a Toast flavor text
    public void makeToast(int baseIndex){
//This if statement gives the base probability of a message triggering
        if(Math.random()<= 1) {
            //These if statements limit the range of meassages that trigger when called by a cerntain event
            if(baseIndex==1) {
                Resources res = getResources();
                String[] flavors = res.getStringArray(R.array.flavor_toasts);
                Random rn = new Random();
                int index = rn.nextInt(8 - 0 + 1) + 0;

                Toast toast = Toast.makeText(getApplicationContext(), flavors[index], Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
            if(baseIndex==2) {
                Resources res = getResources();
                String[] flavors = res.getStringArray(R.array.flavor_toasts);
                Random rn = new Random();
                int index = rn.nextInt(9);

                Toast toast = Toast.makeText(getApplicationContext(), flavors[index], Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        }

    }



    public void flipTurn() {
        currentTurn = !currentTurn;
        TextView turnInfo = (TextView) findViewById(R.id.currentTurnFriend);
        if (currentTurn) {
            turnInfo.setText("Player 1's Turn");
        } else {
            turnInfo.setText("Player 2's Turn");
        }



    }

    public void checkWin(Board board) {
        if (board.p2Remaining() == 0) {
            this.winner = this.currentTurn;
            showWinBanner();
            this.gameOver = true;
        } else if (board.p1Remaining() == 0) {
            this.winner = this.currentTurn;
            showWinBanner();
            this.gameOver = true;
        }

        //TODO: implement the win if opponent has no moves rule
    }

    public void showWinBanner() {
        TextView banner = (TextView) findViewById(R.id.victoryBanner);
        if (this.winner) { //is player1
            banner.setText(R.string.p1_victory);
            makeToast(2);
        } else {
            banner.setText(R.string.p2_victory);
            makeToast(2);
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

    public void returnToMain(View v) {
        finish();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PlayFriendHome Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
