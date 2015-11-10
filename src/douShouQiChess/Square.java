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
		if(this.row <= 0 || this.row > 9 || this.column <= 0) {
			return false;
		}
		return true;
	}

}
