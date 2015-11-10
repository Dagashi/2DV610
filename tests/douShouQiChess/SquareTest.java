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
		square = new Square(3, 10);
		assertEquals(false, square.isValid());
	}

}
