package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {
	private Square square;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenArgumentsAreZero() {
		square = new Square(0,0);
	}

}
