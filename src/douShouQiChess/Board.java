package douShouQiChess;

import java.util.HashMap;

public class Board {
	public final HashMap<Square, Piece> positions;

	public Board() {
		positions = new HashMap<Square, Piece>();
	}

	/**
	 * This method adds the piece to the square at the board.
	 * Used for initialize of the board.
	 * @param Square position
	 * @param Piece piece
	 */
	public void setPieceAt(Square position, Piece piece) {
		if(position.isValid()) {
			positions.put(position, piece);
		}
	}

	/**
	 * This method returns the Piece on the given square if any.
	 * @param Square square
	 * @return the piece at the square.
	 */
	public Piece getPieceAt(Square square) {
		return positions.get(square);
	}
	
	public Piece removePieceAt(Square square) {
		// TODO Auto-generated method stub
		return null;
	}

}
