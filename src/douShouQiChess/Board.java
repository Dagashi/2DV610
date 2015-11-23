package douShouQiChess;

import java.util.HashMap;

import douShouQiChess.Piece.Animal;
import douShouQiChess.Square.Type;

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
			//If a RAT is trying to move to a WATER-Square then move it as normal.
			if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.RAT) {
				movePiece(from, to);
			}
			//Check if a TIGER moves to water and wants to jump it.
			else if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.TIGER) {
				tryJumpWater(from, to);
			}
			//Check if a LION moves to water and wants to jump it.
			else if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.LION) {
				tryJumpWater(from, to);
			}
			//Check if the Square is not a WATER-Square.
			else if(to.getType() != Type.WATER) {
				//If the square is a DEN, then check colors, if it can move there for victory or illegal move.
				if(to.getType() == Type.DEN) {
					if(to.getOwner().toString() != getPieceAt(from).getColor().toString()) {
						movePiece(from, to);
						//TODO: Register Victory!
					}
				}
				else {
					//If the square contains another Piece then try to capture it.
					if(!isSquareEmpty(to)) {
						tryCapturePiece(from, to);
					}
					//Otherwise move it normally.
					else {
						movePiece(from, to);
					}
				}
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
			//If a RAT attacks an ELEPHANT it captures it since according to legend:
			//"A RAT can go into the ELEPHANTS ear when it is asleep and eats it's brain"
			if(attackingPiece.getAnimal() == Animal.RAT && defendingPiece.getAnimal() == Animal.ELEPHANT) {
				//A RAT cannot capture ELEPHANT from WATER.
				if(from.getType() != Type.WATER) {
					capturePiece(from, to);
				}
			}
			//If the tile that are attacked are the attackers TRAP then capture without regard to values.
			else if(to.getType() == Type.TRAP && to.getOwner().toString() == attackingPiece.getColor().toString()) {
				capturePiece(from, to);
			}
			//If the value of the attacking piece is higher or equal to the defending piece then it will be captured.
			else if(attackingPiece.getValue() >= defendingPiece.getValue()) {
				capturePiece(from, to);
			}
		}
	}
	
	/**
	 * This method capture the piece.
	 * @param Square from
	 * @param Square to
	 */
	private void capturePiece(Square from, Square to) {
		//TODO: record that the piece was captured.
		movePiece(from, to);
	}
	
	/**
	 * This method contains logic for TIGER or LION jumping the water.
	 * @param Square from
	 * @param Square to
	 */
	public void tryJumpWater(Square from, Square to) {
		//If they try to jump over water to the left.
		if(to.equals(from.stepLeft())) {
			Square jumpTo = new Square(from.getRow(), 1);
			
			if(isSquareEmpty(to) && isSquareEmpty(to.stepLeft())) {
				if(!isSquareEmpty(jumpTo)) {
					tryCapturePiece(from, jumpTo);
				}
				else {
					movePiece(from, jumpTo);
				}
			}
		}
		//If they try to jump over water to the right.
		else if(to.equals(from.stepRight())) {
			Square jumpTo = new Square(from.getRow(), 7);
			
			if(!isSquareEmpty(jumpTo)) {
				tryCapturePiece(from, jumpTo);
			}
			else {
				movePiece(from, jumpTo);
			}
		}
		//If they try to jump over water upwards.
		else if(to.equals(from.stepUp())) {
			Square jumpTo = new Square(3, from.getColumn());
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepUp()) && isSquareEmpty(to.stepUp().stepUp())) {
				if(!isSquareEmpty(jumpTo)) {
					tryCapturePiece(from, jumpTo);
				}
				else {
					movePiece(from, jumpTo);
				}
			}
		}
		//If they try to jump over water downwards.
		else if(to.equals(from.stepDown())) {
			Square jumpTo = new Square(7, from.getColumn());
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepDown()) && isSquareEmpty(to.stepDown().stepDown())) {
				if(!isSquareEmpty(jumpTo)) {
					tryCapturePiece(from, jumpTo);
				}
				else {
					movePiece(from, jumpTo);
				}
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
