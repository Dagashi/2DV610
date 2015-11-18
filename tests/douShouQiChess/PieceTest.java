package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import douShouQiChess.Piece.Animal;
import douShouQiChess.Piece.Color;

public class PieceTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void ShouldFailIfThePieceHasTheWrongValue() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		assertEquals(1, piece.getValue());
	}
	
	@Test
	public void ShouldFailIfThePieceHasTheWrongColor() {
		Piece piece = new Piece(Animal.RAT,Color.DARK);
		assertEquals(Color.DARK, piece.getColor());
	}

}
