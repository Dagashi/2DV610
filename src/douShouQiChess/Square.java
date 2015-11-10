package douShouQiChess;

public class Square {
	private int row;
	private int column;
	
	public Square(int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	
	/**
	 * This method checks if the square is a valid one for this board.
	 * @return boolean.
	 */
	public boolean isValid() {
		if(this.row <= 0 || this.row > 9 || this.column <= 0 || this.column > 7) {
			return false;
		}
		return true;
	}


	/**
	 * This method return the square 1 space upwards if it is valid, else returns null.
	 * @return Square || null.
	 */
	public Square stepUp() {
		Square tmpSquare = new Square(this.row + 1, this.column);
		
		if(!tmpSquare.isValid()) {
			return null;
		}
		
		return tmpSquare;
	}


	/**
	 * This method return the square 1 space downwards if it is valid, else returns null.
	 * @return Square || null.
	 */
	public Object stepDown() {
		Square tmpSquare = new Square(this.row - 1, this.column);
		
		if(!tmpSquare.isValid()) {
			return null;
		}
		
		return tmpSquare;
	}


	/**
	 * This method return the square 1 space to the right, if it is valid, else returns null.
	 * @return Square || null.
	 */
	public Square stepRight() {
		Square tmpSquare = new Square(this.row, this.column + 1);
		
		if(!tmpSquare.isValid()) {
			return null;
		}
		
		return tmpSquare;
	}


	public Square stepLeft() {
		Square tmpSquare = new Square(this.row, this.column - 1);
		
		return tmpSquare;
	}
	
	@Override
	public boolean equals(Object obj) {
		Square other = (Square) obj;
		
		if (this.column != other.column) {
			return false;
		}
		if (this.row != other.row) {
			return false;
		}
		return true;
	}

}
