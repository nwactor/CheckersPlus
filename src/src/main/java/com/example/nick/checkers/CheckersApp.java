package com.example.nick.checkers;

import android.app.Application;
import android.graphics.drawable.Drawable;

/**
 * Created by Nick on 12/1/2016.
 */

public class CheckersApp extends Application {
    //default values
    private String difficulty = "Easy";
    private String p1Color = "Blue";
    private String p2Color = "Green";
    private Drawable p1Piece; //= getResources().getDrawable(R.drawable.blue_piece);
    private Drawable p1King; //= getResources().getDrawable(R.drawable.blue_king);
    private Drawable p2Piece; //= getResources().getDrawable(R.drawable.green_piece);
    private Drawable p2King; //= getResources().getDrawable(R.drawable.green_king);

    public String getDifficulty(){
        return difficulty;
    }
    public void setDifficulty(String s){
        difficulty = s;
    }

    public String getP1Color(){
        return p1Color;
    }
    public void setP1Color(String s){
        p1Color = s;
        switch(p1Color) {
            case "Blue": p1Piece = getResources().getDrawable(R.drawable.blue_piece);
                p1King = getResources().getDrawable(R.drawable.blue_king);
                break;
            case "Green": p1Piece = getResources().getDrawable(R.drawable.green_piece);
                p1King = getResources().getDrawable(R.drawable.green_king);
                break;
            case "Red": p1Piece = getResources().getDrawable(R.drawable.red_piece);
                p1King = getResources().getDrawable(R.drawable.red_king);
                break;
            case "Purple": p1Piece = getResources().getDrawable(R.drawable.purple_piece);
                p1King = getResources().getDrawable(R.drawable.purple_king);
                break;
            case "Orange": p1Piece = getResources().getDrawable(R.drawable.orange_piece);
                p1King = getResources().getDrawable(R.drawable.orange_king);
                break;
            case "White": p1Piece = getResources().getDrawable(R.drawable.white_piece);
                p1King = getResources().getDrawable(R.drawable.white_king);
                break;
            case "Tan": p1Piece = getResources().getDrawable(R.drawable.tan_piece);
                p1King = getResources().getDrawable(R.drawable.tan_king);
                break;
        }
    }

    public String getP2Color(){
        return p2Color;
    }
    public void setP2Color(String s){
        p2Color = s;
        switch(p2Color) {
            case "Blue": p2Piece = getResources().getDrawable(R.drawable.blue_piece);
                p2King = getResources().getDrawable(R.drawable.blue_king);
                break;
            case "Green": p2Piece = getResources().getDrawable(R.drawable.green_piece);
                p2King = getResources().getDrawable(R.drawable.green_king);
                break;
            case "Red": p2Piece = getResources().getDrawable(R.drawable.red_piece);
                p2King = getResources().getDrawable(R.drawable.red_king);
                break;
            case "Purple": p2Piece = getResources().getDrawable(R.drawable.purple_piece);
                p2King = getResources().getDrawable(R.drawable.purple_king);
                break;
            case "Orange": p2Piece = getResources().getDrawable(R.drawable.orange_piece);
                p2King = getResources().getDrawable(R.drawable.orange_king);
                break;
            case "White": p2Piece = getResources().getDrawable(R.drawable.white_piece);
                p2King = getResources().getDrawable(R.drawable.white_king);
                break;
            case "Tan": p2Piece = getResources().getDrawable(R.drawable.tan_piece);
                p2King = getResources().getDrawable(R.drawable.tan_king);
                break;
        }
    }

    public Drawable getP1Piece() {
        if(p1Piece == null) { //default
            return getResources().getDrawable(R.drawable.blue_piece);
        }
        return this.p1Piece;
    }

    public Drawable getP1King() {
        if(p1King == null) { //default
            return getResources().getDrawable(R.drawable.blue_king);
        }
        return this.p1King;
    }

    public Drawable getP2Piece() {
        if(p2Piece == null) { //default
            return getResources().getDrawable(R.drawable.green_piece);
        }
        return this.p2Piece;
    }

    public Drawable getP2King() {
        if(p2King == null) { //default
            return getResources().getDrawable(R.drawable.green_king);
        }
        return this.p2King;
    }
}
