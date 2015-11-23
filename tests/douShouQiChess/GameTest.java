package douShouQiChess;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ShouldFailIfTheInitMethodDoesNotSetOutSixteenPieceOnTheBoard() {
		Board mockedBoard = mock(Board.class);
		Game game = new Game();
		game.initialize(mockedBoard);
		verify(mockedBoard, times(16)).setPieceAt(anyObject(), anyObject());
	}

}
