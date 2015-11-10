package douShouQiChess;

public class Square {

	public Square(int row, int col) {
		if(row == 0 || col == 0) {
			throw new IllegalArgumentException();
		}
	}

}
