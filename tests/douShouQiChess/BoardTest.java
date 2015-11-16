package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void ShouldFailIfThePieceIsNotAddedToBoard() {
		Piece piece = new Piece();
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(1, board.positions.size());
	}
	
	@Test
	public void ShouldFailIfTheSquareIsNotAValidPosition() {
		Piece piece = new Piece();
		Square square = new Square(12, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(0, board.positions.size());
	}
	
	@Test
	public void ShouldFailIfTheSquareDoesNotReturnTheRightPiece() {
		Piece piece = new Piece();
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		assertEquals(piece, board.getPieceAt(square));
	}
	
	@Test
	public void ShouldFailIfThePieceIsNotRemoved() {
		Piece piece = new Piece();
		Square square = new Square(2, 3);
		
		board.setPieceAt(square, piece);
		
		assertEquals(piece, board.removePieceAt(square));
		assertEquals(0, board.positions.size());
	}

}
