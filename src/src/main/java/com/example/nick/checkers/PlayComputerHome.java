package com.example.nick.checkers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PlayComputerHome extends AppCompatActivity { //might want to have it extend playFriendHome
    protected boolean gameOver;
    protected boolean playerTurn;
    private BoardFragment game;
    private boolean winner;
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_computer_home);
        this.game = (BoardFragment) getFragmentManager().findFragmentById(R.id.checkerBoard);
        playerTurn = true; //true = player1, false = player2
        this.difficulty = 0;
    }

    public void flipTurn() {
        playerTurn = !playerTurn;
        if(!playerTurn && !checkWin(game.getBoardView().getBoard())) {
            getAIMove();
        }
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

    public boolean checkWin(Board board) {
        if(board.p2Remaining() == 0) {
            this.winner = !this.playerTurn;
            showWinBanner();
            this.gameOver = true;
            return true;
        } else if(board.p1Remaining() == 0) {
            this.winner = !this.playerTurn;
            showWinBanner();
            this.gameOver = true;
            return true;
        }

        //win if opponent has no moves rule
        boolean hasMoves = false;
        for(Square square : board.getComputerPieces()) {
            if(square.getAvailableMoves().size() > 0) {
                hasMoves = true;
                break;
            }
        }
        if(hasMoves == false) {
            this.winner = !this.playerTurn;
            showWinBanner();
            this.gameOver = true;
            return true;
        }

        return false;
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
        playerTurn = true; //true = player1, false = player2

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
                        PlayComputerHome.this.resetGame();
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
                        PlayComputerHome.this.winner = !PlayComputerHome.this.playerTurn;
                        PlayComputerHome.this.showWinBanner();
                        PlayComputerHome.this.gameOver = true;
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
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
        //[0] is the square of the piece that will move, [1] is the destination
        Square[] move = new Square[2];
        //put all of the computer's pieces into an arrayList
        ArrayList<Square> pieces = this.game.getBoardView().getBoard().getComputerPieces();

        //get a random int to chose the piece that will be moved
        Random r = new Random();
        boolean moved = false;

        while(!moved) {
            //get the piece at the random number
            int index = r.nextInt(pieces.size());
            //check if the piece has moves
            if(pieces.get(index).getAvailableMoves().size() > 0) {
                //pick a random move from the list of available ones
                int chosen = r.nextInt(pieces.get(index).getAvailableMoves().size());
                int i = 0;

                move[0] = pieces.get(index);

                //get that square out of the hash set
                for(Square square : pieces.get(index).getAvailableMoves()) {
                    if(i != chosen) {
                        i++;
                    } else {
                        move[1] = square;
                    }
                }
                moved = true;
            }
        }

        //make the move; should always work
        move[0].move(move[1]);
        checkWin(this.game.getBoardView().getBoard());
        flipTurn();
    }

    private void getHardAIMove() {
        Square[] move = new Square[2];
    }

}
