package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {
	private Square square;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void shouldGiveFalseOnZeroAsArguments() {
		square = new Square(0, 0);
		assertEquals(false, square.isValid());
	}
	
	@Test
	public void shouldGiveFalseOnNegativeIntArguments() {
		square = new Square(-1, -3);
		assertEquals(false, square.isValid());
	}
	
	@Test
	public void shouldGiveFalseOnRowArgumentToHigh() {
		square = new Square(10, 5);
		assertEquals(false, square.isValid());
	}
	
	@Test
	public void shouldGiveFalseOnColumnArgumentToHigh() {
		square = new Square(3, 8);
		assertEquals(false, square.isValid());
	}
	
	@Test
	public void shouldFailIfTheStepUpSquareIsNotaValidSquare() {
		square = new Square(9, 4);
		assertNull(square.stepUp());
	}
	
	@Test
	public void shouldFailIfTheStepDownSquareIsNotaValidSquare() {
		square = new Square(1, 4);
		assertNull(square.stepDown());
	}
	
	

}
