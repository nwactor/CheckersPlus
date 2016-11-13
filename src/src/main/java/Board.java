import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.nick.checkers.R;

/**
 * Created by Nick on 11/10/2016.
 */
public class Board extends View {
    private int rows;
    private int columns;
    private Square[][] squares;

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Board,
                0, 0);

        try {
            mRows = a.getInteger(R.styleable.Board_rows, 16);
            mColumns = a.getInteger(R.styleable.Board_columns, 16);
        } finally {
            a.recycle();
        }
        */
        this.rows = 16;
        this.columns = 16;
        this.squares = new Square[rows][columns];

        //ascii code for A
        //char startLetter = 65;


        //apparently only half of the square are used, so might want to remove those
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                boolean redOrBlack = true;
                if(i % 2 == 0) {
                    if(j % 2 == 0) { redOrBlack = false; } //square should be black
                } else {
                    if(j % 2 != 0) { redOrBlack = false; } //square should be black
                }
                squares[i - 1][j - 1] = new Square(redOrBlack, j, i, this);
            }
            //use acscii char codes to increment letter
        }

        //place black pieces for game start
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    squares[i - 1][j - 1].setOccupant(new Piece(false, false, squares[i - 1][j - 1]));
                }
            }
        }
        //place red pieces for game start
        for(int i = rows; i > rows - 3; i--) {
            for(int j = 1; j <= this.columns; j++) {
                if(squares[i - 1][j - 1].isBlack()) {
                    squares[i - 1][j - 1].setOccupant(new Piece(true, false, squares[i - 1][j - 1]));
                }
            }
        }

        //draw the board
        init();

    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public Square getSquare(int xPosition, int yPosition) {
        return this.squares[xPosition - 1][yPosition - 1];
    }

    private void init() {
        Paint boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boardPaint.setStyle(Paint.Style.FILL);
        //boardPaint.setColor(5);

    }
}
