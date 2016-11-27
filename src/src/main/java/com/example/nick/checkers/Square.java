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
    private Drawable display;
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
            this.display = getResources().getDrawable(R.drawable.square_red);
        } else { //black square
            this.display = getResources().getDrawable(R.drawable.square_black);
        }
        setImageDrawable(display);
        this.xSize = this.getWidth();
        this.ySize = this.getHeight();
        //id?
    }

    public Piece getOccupant() {
        return this.occupant;
    }

    public boolean hasOccupant() {
        return !(this.occupant == null);
    }

    public void setOccupant(Piece occupant) {
        this.occupant = occupant;

        //update graphic
        if(occupant.isP1()) {
            //there should be a global variable that stores the chosen p1 color
            if(occupant.isKing()) {
                this.display = getResources().getDrawable(R.drawable.blue_king);
                setImageDrawable(this.display);
            } else {
                this.display = getResources().getDrawable(R.drawable.blue_piece);
                setImageDrawable(this.display);
            }
        } else { //belongs to player 2
            if(occupant.isKing()) {
                this.display = getResources().getDrawable(R.drawable.green_king);
                setImageDrawable(this.display);
            } else {
                this.display = getResources().getDrawable(R.drawable.green_piece);
                setImageDrawable(this.display);
            }
        }
    }

    public void removeOccupant() {
        this.occupant = null;

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
