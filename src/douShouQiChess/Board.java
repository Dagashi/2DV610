package douShouQiChess;

import java.util.HashMap;

public class Board {
	public final HashMap<Square, Piece> positions;

	public Board() {
		positions = new HashMap<Square, Piece>();
	}

	/**
	 * This method adds the Piece to the Square at the Board.
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
	 * This method returns the Piece at the given Square if any.
	 * @param Square square
	 * @return The piece at the square.
	 */
	public Piece getPieceAt(Square square) {
		return positions.get(square);
	}
	
	/**
	 * This method removes the Piece at the given Square if any.
	 * @param Square square
	 * @return The piece that was just removed.
	 */
	public Piece removePieceAt(Square square) {
		return positions.remove(square);
	}

	/**
	 * This method moves the Piece from the Square "from" to the Square "to"
	 * @param Square from
	 * @param Square to
	 */
	public void movePiece(Square from, Square to) {
		if(to.isValid()) {
			Piece piece = getPieceAt(from);
			removePieceAt(from);
			setPieceAt(to, piece);
		}
	}

	/**
	 * This method returns true id the Square is empty, else false.
	 * @param Square square
	 * @return boolean
	 */
	public boolean isSquareEmpty(Square square) {
		if(square == null) {
			return false;
		}
		
		Piece piece = getPieceAt(square);
		if(piece != null) {
			return false;
		}
		else {
			return true;
		}
	}

}
