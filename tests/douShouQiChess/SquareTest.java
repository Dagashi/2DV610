package douShouQiChess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import douShouQiChess.Square.Type;

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
	public void shouldFailIfTheStepUpSquareIsWrong() {
		square = new Square(5, 4);
		Square upSquare = new Square(4, 4); 
		assertEquals(upSquare, square.stepUp());
	}
	
	@Test
	public void shouldFailIfTheStepUpSquareIsNotaValidSquare() {
		square = new Square(1, 4);
		assertNull(square.stepUp());
	}
	
	@Test
	public void shouldFailIfTheStepDownSquareIsWrong() {
		square = new Square(5, 4);
		Square downSquare = new Square(6, 4); 
		assertEquals(downSquare, square.stepDown());
	}
	
	@Test
	public void shouldFailIfTheStepDownSquareIsNotaValidSquare() {
		square = new Square(9, 4);
		assertNull(square.stepDown());
	}
	
	@Test
	public void shouldFailIfTheStepRightSquareIsWrong() {
		square = new Square(5, 4);
		Square rightSquare = new Square(5, 5); 
		assertEquals(rightSquare, square.stepRight());
	}
	
	@Test
	public void shouldFailIfTheStepRightSquareIsNotaValidSquare() {
		square = new Square(5, 7);
		assertNull(square.stepRight());
	}
	
	@Test
	public void shouldFailIfTheStepLeftSquareIsWrong() {
		square = new Square(5, 4);
		Square leftSquare = new Square(5, 3); 
		assertEquals(leftSquare, square.stepLeft());
	}
	
	@Test
	public void shouldFailIfTheStepLeftSquareIsNotaValidSquare() {
		square = new Square(5, 1);
		assertNull(square.stepLeft());
	}
	
	@Test
	public void shouldFailIfTheSquareTypeIsNotRight() {
		square = new Square(4, 2);
		assertEquals(Type.WATER, square.getType());
		
		square = new Square(1, 4);
		assertEquals(Type.DEN, square.getType());
		
		square = new Square(9, 3);
		assertEquals(Type.TRAP, square.getType());
		
		square = new Square(5, 4);
		assertEquals(Type.REGULAR, square.getType());
	}
	

}
