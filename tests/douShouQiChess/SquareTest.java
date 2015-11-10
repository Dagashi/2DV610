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

}
