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

}
