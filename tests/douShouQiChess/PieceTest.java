package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import douShouQiChess.Piece.Animal;

public class PieceTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		Piece piece = new Piece(Animal.RAT);
		assertEquals(1, piece.getValue());
	}

}
