package douShouQiChess;

import java.util.HashMap;

import douShouQiChess.Piece.Animal;

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
	 * This method tries if we can move the piece or if we should capture etc.
	 * @param Square from
	 * @param Square to
	 */
	public void tryMovePiece(Square from, Square to) {
		if(to.isValid()) {
			if(!isSquareEmpty(to)) {
				tryCapturePiece(from, to);
			}
			else {
				movePiece(from, to);
			}
		}
	}
	
	/**
	 * This method moves the Piece from the Square "from" to the Square "to"
	 * @param Square from
	 * @param Square to
	 */
	private void movePiece(Square from, Square to) {
		Piece piece = getPieceAt(from);
		removePieceAt(from);
		setPieceAt(to, piece);
	}
	
	
	/**
	 * This method contains the capturing-logic like which piece can capture who etc.
	 * @param Square from
	 * @param Square to
	 */
	private void tryCapturePiece(Square from, Square to) {
		Piece attackingPiece = getPieceAt(from);
		Piece defendingPiece = getPieceAt(to);
		
		//Only try to capture if the attacking and defending pieces are different color.
		if(attackingPiece.getColor() != defendingPiece.getColor()) {
			if(attackingPiece.getAnimal() == Animal.RAT && defendingPiece.getAnimal() == Animal.ELEPHANT) {
				//TODO: record that the piece was captured.
				movePiece(from, to);
			}
			//If the value of the attacking piece is higher or equal to the defending piece then it will be captured.
			else if(attackingPiece.getValue() >= defendingPiece.getValue()) {
				//TODO: record that the piece was captured.
				movePiece(from, to);
			}
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
