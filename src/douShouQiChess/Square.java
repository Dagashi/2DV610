package douShouQiChess;

public class Square {
	private int row;
	private int column;
	private Type squareType;
	private Owner owner;
	
	public Square(int row, int col) {
		this.row = row;
		this.column = col;
		this.squareType = this.checkMyTypeAndOwner();
	}
	
	public enum Owner {
		DARK, LIGHT, NEUTRAL
	}
	
	/**
	 * This method checks what type of square it is.
	 * Used at constructor.
	 * @return Type
	 */
	private Type checkMyTypeAndOwner() {
		if(this.row == 4 || this.row == 5 || this.row == 6) {
			if(this.column == 2 || this.column == 3 || this.column == 5 || this.column == 6) {
				this.owner = Owner.NEUTRAL;
				return Type.WATER;
			}
			else {
				this.owner = Owner.NEUTRAL;
				return Type.REGULAR;
			}
		}
		else if(this.row == 1) {
			if(this.column == 4) {
				this.owner = Owner.LIGHT;
				return Type.DEN;
			}
			else if(this.column == 3 || this.column == 5) {
				this.owner = Owner.LIGHT;
				return Type.TRAP;
			}
			else {
				this.owner = Owner.NEUTRAL;
				return Type.REGULAR;
			}
		}
		else if(this.row == 9) {
			if(this.column == 4) {
				this.owner = Owner.DARK;
				return Type.DEN;
			}
			else if(this.column == 3 || this.column == 5) {
				this.owner = Owner.DARK;
				return Type.TRAP;
			}
			else {
				this.owner = Owner.NEUTRAL;
				return Type.REGULAR;
			}
		}
		else if(this.row == 2) {
			if(this.column == 4) {
				this.owner = Owner.LIGHT;
				return Type.TRAP;
			}
			else {
				this.owner = Owner.NEUTRAL;
				return Type.REGULAR;
			}
		}
		else if(this.row == 8) {
			if(this.column == 4) {
				this.owner = Owner.DARK;
				return Type.TRAP;
			}
			else {
				this.owner = Owner.NEUTRAL;
				return Type.REGULAR;
			}
		}
		else {
			this.owner = Owner.NEUTRAL;
			return Type.REGULAR;
		}
	}

	public enum Type {
		REGULAR, WATER, TRAP, DEN;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public Type getType() {
		return squareType;
	}
	
	public Owner getOwner() {
		return owner;
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
	public Square stepDown() {
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

	/**
	 * This method return the square 1 space to the left, if it is valid, else returns null.
	 * @return Square || null.
	 */
	public Square stepLeft() {
		Square tmpSquare = new Square(this.row, this.column - 1);
		
		if(!tmpSquare.isValid()) {
			return null;
		}
		
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
