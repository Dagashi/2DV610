package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import douShouQiChess.Piece.Animal;
import douShouQiChess.Piece.Color;

public class BoardTest {
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void ShouldFailIfThePieceIsNotAddedToBoard() {
		Piece piece = new Piece(Animal.RAT, Color.DARK);
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(1, board.positions.size());
	}
	
	@Test
	public void ShouldFailIfTheSquareIsNotAValidPosition() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(12, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(0, board.positions.size());
	}
	
	@Test
	public void ShouldFailIfTheSquareDoesNotReturnTheRightPiece() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(piece, board.getPieceAt(square));
	}
	
	@Test
	public void ShouldFailIfThePieceIsNotRemoved() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		
		assertEquals(piece, board.removePieceAt(square));
		assertEquals(0, board.positions.size());
	}
	
	@Test
	public void ShouldFailIfThePieceIsNotMoved() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square from = new Square(2, 3);
		Square to = from.stepUp();
		
		board.setPieceAt(from, piece);
		board.tryMovePiece(from, to);
		
		assertEquals(piece, board.getPieceAt(to));
		assertEquals(null, board.getPieceAt(from));
	}
	
	@Test
	public void ShouldFailIfTheSquareIsFree() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(2, 3);
		Square otherSquare = square.stepUp();
		
		board.setPieceAt(square, piece);
		
		assertEquals(false, board.isSquareEmpty(square));
		assertEquals(true, board.isSquareEmpty(otherSquare));
	}
	
	@Test
	public void ShouldFailIfThePieceWithLowerValueCaptureAPieceWithHigherValue() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(2, 3);
		board.setPieceAt(square, piece);
		
		Piece piece2 = new Piece(Animal.CAT,Color.LIGHT);
		Square otherSquare = square.stepUp();
		board.setPieceAt(otherSquare, piece2);
		
		board.tryMovePiece(square, otherSquare);
		
		assertEquals(piece2, board.getPieceAt(otherSquare));
	}
	
	@Test
	public void ShouldFailIfAPieceCapturesAPieceWithSameColor() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		Square square = new Square(2, 3);
		board.setPieceAt(square, piece);
		
		Piece piece2 = new Piece(Animal.CAT,Color.DARK);
		Square otherSquare = square.stepUp();
		board.setPieceAt(otherSquare, piece2);
		
		board.tryMovePiece(otherSquare, square);
		
		assertEquals(piece, board.getPieceAt(square));
	}
	
	

}
