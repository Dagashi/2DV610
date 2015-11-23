package douShouQiChess;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

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
	
	@Test
	public void ShouldFailIfStartDoesNotExecuteInitializeMethodOnce() {
		Game game = new Game();
		Game spy = spy(game);
		spy.start();
		verify(spy, times(1)).initialize(anyObject());
	}
	
	@Test
	public void ShouldFailIfStartDontAddTwoPlayers() {
		Game game = new Game();
		game.start();
		assertEquals(2, game.players.size());
	}
	
	@Test
	public void ShouldFailIfTheMessageInStartIsNotWelcomeMessage() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(startsWith("Welcome"));
	}

}
