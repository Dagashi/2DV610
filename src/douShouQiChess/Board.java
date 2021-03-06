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
	public boolean tryMovePiece(Square from, Square to) {
		if(to.isValid()) {
			//If a RAT is trying to move to a WATER-Square then move it as normal.
			if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.RAT) {
				boolean success = movePiece(from, to);
				return success;
			}
			//Check if a TIGER moves to water and wants to jump it.
			else if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.TIGER) {
				boolean success = tryJumpWater(from, to);
				return success;
			}
			//Check if a LION moves to water and wants to jump it.
			else if(to.getType() == Type.WATER && getPieceAt(from).getAnimal() == Animal.LION) {
				boolean success = tryJumpWater(from, to);
				return success;
			}
			//Check if the Square is not a WATER-Square.
			else if(to.getType() != Type.WATER) {
				//If the square is a DEN, then check colors, if it can move there for victory or illegal move.
				if(to.getType() == Type.DEN) {
					if(to.getOwner().toString() != getPieceAt(from).getColor().toString()) {
						boolean success = movePiece(from, to);
						return success;
						//TODO: Register Victory!
					}
					else {
						return false;
					}
				}
				else {
					//If the square contains another Piece then try to capture it.
					if(!isSquareEmpty(to)) {
						boolean success = tryCapturePiece(from, to);
						return success;
					}
					//Otherwise move it normally.
					else {
						boolean success = movePiece(from, to);
						return success;
					}
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method moves the Piece from the Square "from" to the Square "to"
	 * @param Square from
	 * @param Square to
	 * @return boolean success (if action succeeded).
	 */
	private boolean movePiece(Square from, Square to) {
		Piece piece = getPieceAt(from);
		removePieceAt(from);
		setPieceAt(to, piece);
		if(getPieceAt(to) == piece) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * This method contains the capturing-logic like which piece can capture who etc.
	 * @param Square from
	 * @param Square to
	 * @return boolean success (if action succeeded).
	 */
	private boolean tryCapturePiece(Square from, Square to) {
		Piece attackingPiece = getPieceAt(from);
		Piece defendingPiece = getPieceAt(to);
		
		//Only try to capture if the attacking and defending pieces are different color.
		if(attackingPiece.getColor() != defendingPiece.getColor()) {
			//If a RAT attacks an ELEPHANT it captures it since according to legend:
			//"A RAT can go into the ELEPHANTS ear when it is asleep and eats it's brain"
			if(attackingPiece.getAnimal() == Animal.RAT && defendingPiece.getAnimal() == Animal.ELEPHANT) {
				//A RAT cannot capture ELEPHANT from WATER.
				if(from.getType() != Type.WATER) {
					boolean success = capturePiece(from, to);
					return success;
				}
				return false;
			}
			//If the tile that are attacked are the attackers TRAP then capture without regard to values.
			else if(to.getType() == Type.TRAP && to.getOwner().toString() == attackingPiece.getColor().toString()) {
				boolean success = capturePiece(from, to);
				return success;
			}
			//If the value of the attacking piece is higher or equal to the defending piece then it will be captured.
			else if(attackingPiece.getValue() >= defendingPiece.getValue()) {
				boolean success = capturePiece(from, to);
				return success;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method capture the piece.
	 * @param Square from
	 * @param Square to
	 * @return boolean success (if action succeeded).
	 */
	private boolean capturePiece(Square from, Square to) {
		//TODO: record that the piece was captured.
		boolean success = movePiece(from, to);
		return success;
	}
	
	/**
	 * This method contains logic for TIGER or LION jumping the water.
	 * @param Square from
	 * @param Square to
	 * @return boolean success (if action succeeded).
	 */
	public boolean tryJumpWater(Square from, Square to) {
		//If they try to jump over water to the left.
		if(to.equals(from.stepLeft())) {
			Square jumpTo = new Square(from.getRow(), 1);
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepLeft())) {
				if(!isSquareEmpty(jumpTo)) {
					boolean success = tryCapturePiece(from, jumpTo);
					return success;
				}
				else {
					boolean success = movePiece(from, jumpTo);
					return success;
				}
			}
			else {
				return false;
			}
		}
		//If they try to jump over water to the right.
		else if(to.equals(from.stepRight())) {
			Square jumpTo = new Square(from.getRow(), 7);
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepRight())) {
				if(!isSquareEmpty(jumpTo)) {
					boolean success = tryCapturePiece(from, jumpTo);
					return success;
				}
				else {
					boolean success = movePiece(from, jumpTo);
					return success;
				}
			}
			else {
				return false;
			}
		}
		//If they try to jump over water upwards.
		else if(to.equals(from.stepUp())) {
			Square jumpTo = new Square(3, from.getColumn());
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepUp()) && isSquareEmpty(to.stepUp().stepUp())) {
				if(!isSquareEmpty(jumpTo)) {
					boolean success = tryCapturePiece(from, jumpTo);
					return success;
				}
				else {
					boolean success = movePiece(from, jumpTo);
					return success;
				}
			}
			else {
				return false;
			}
		}
		//If they try to jump over water downwards.
		else if(to.equals(from.stepDown())) {
			Square jumpTo = new Square(7, from.getColumn());
			//Check if there is a piece in the water when jumping.
			if(isSquareEmpty(to) && isSquareEmpty(to.stepDown()) && isSquareEmpty(to.stepDown().stepDown())) {
				if(!isSquareEmpty(jumpTo)) {
					boolean success = tryCapturePiece(from, jumpTo);
					return success;
				}
				else {
					boolean success = movePiece(from, jumpTo);
					return success;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
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

	/**
	 * This method prints the board to console with all the pieces in the right position.
	 */
	public void printBoard() {
		String boardString = "    1    2    3    4    5    6    7    \n  -----------------------------------\n";
		for (int row = 1; row < 10; row++) {
			boardString += row+"|";
			for (int col = 1; col < 8; col++) {
				Square tmpSquare = new Square(row, col);
				if(!isSquareEmpty(tmpSquare)) {
					Piece tmpPiece = getPieceAt(tmpSquare);
					if(tmpPiece.getAnimal() == Animal.RAT) {
						boardString += " R  |";
					}
					else if(tmpPiece.getAnimal() == Animal.CAT) {
						boardString += " C  |";
					}
					else if(tmpPiece.getAnimal() == Animal.WOLF) {
						boardString += " W  |";
					}
					else if(tmpPiece.getAnimal() == Animal.DOG) {
						boardString += " D  |";
					}
					else if(tmpPiece.getAnimal() == Animal.LEOPARD) {
						boardString += " Le |";
					}
					else if(tmpPiece.getAnimal() == Animal.TIGER) {
						boardString += " T  |";
					}
					else if(tmpPiece.getAnimal() == Animal.LION) {
						boardString += " Li |";
					}
					else if(tmpPiece.getAnimal() == Animal.ELEPHANT) {
						boardString += " E  |";
					}
				}
				else if(tmpSquare.getType() == Type.DEN) {
					boardString += " DE |";
				}
				else if(tmpSquare.getType() == Type.TRAP) {
					boardString += " TR |";
				}
				else if(tmpSquare.getType() == Type.WATER) {
					boardString += " \u007E\u007E |";
				}
				else {
					boardString += "    |";
				}
				
			}
			boardString += "\n  ----------------------------------\n";
		}
		System.out.println(boardString);
	}

}
