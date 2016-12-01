package com.example.nick.checkers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PlayComputerHome extends AppCompatActivity implements Runnable { //might want to have it extend playFriendHome
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
        //playGame();
    }

    public void playGame() {
        gameOver = false;
        //Board board = (R.layout.activity_play_computer_home);

        while(!gameOver) {
            if(playerTurn) {
                try {
                    this.wait();
                } catch (InterruptedException e) { }
            } else { //computer's turn
                getAIMove();
            }
        }
    }

    public void run() {
        playGame();
    }

    public void flipTurn() {
        playerTurn = !playerTurn;
    }

    public void checkWin(Board board) {
        if(board.p2Remaining() == 0) {
            this.winner = this.playerTurn;
            showWinBanner();
            this.gameOver = true;
        } else if(board.p1Remaining() == 0) {
            this.winner = this.playerTurn;
            showWinBanner();
            this.gameOver = true;
        }

        //TODO: implement the win if opponent has no moves rule
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
        for(Square square : pieces) {
            pieces.add(square);
        }

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

    private void reset() {

    }

}
