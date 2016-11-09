package Mobile_Apps;

public class Board {
	private int rows;
	private int columns;
	private Square[][] squares;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
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
	
}