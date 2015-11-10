package douShouQiChess;

public class Square {
	private int row;
	private int column;
	
	public Square(int row, int col) {
		this.row = row;
		this.column = col;
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



	public Object stepDown() {
		// TODO Auto-generated method stub
		Object object = new Object();
		return object;
	}

}
