package com.example.nick.checkers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.example.nick.checkers.Board;
import com.example.nick.checkers.Piece;

/**
 * Created by Nick on 11/10/2016.
 */
public class Square extends ImageView {
    //private String name; //might not need a name field at all; can be calculated in-method from xPosition and yPosition
    private boolean color; //true = red, false = black
    private Piece occupant;
    private int xPosition;
    private int yPosition;
    private int xSize;
    private int ySize;
    private Board board;
    private Context context;

    public Square(Context context, boolean color, int xPosition, int yPosition, Board board) {
        super(context);

        this.context = context;
        //this.name = name;
        this.color = color;
        //this.occupant = occupant; //don't set in constructor, set in board's constructor
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.board = board;

        //Load the image
        if(color) { //red square
            Drawable d = getResources().getDrawable(R.drawable.SquareRed);
        } else { //black square
            Drawable d = getResources().getDrawable(R.drawable.SquareBlack);
        }
        //setImageDrawable(d);
        this.xSize = this.getWidth();
        this.ySize = this.getHeight();
        //id?
    }

    public Piece getOccupant() {
        return this.occupant;
    }

    public void setOccupant(Piece occupant) {
        this.occupant = occupant;
    }

    public boolean isRed() {
        return color;
    }

    public boolean isBlack() {
        return !color;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public Board getBoard() {
        return this.board;
    }
}
