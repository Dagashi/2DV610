package douShouQiChess;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	@Before
	public void setUp() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("7,1,up".getBytes());
		System.setIn(in);
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
	
	@Test
	public void ShouldFailIfStartDoesntCallToNextTurnOnce() {
		Game game = new Game();
		Game spy = spy(game);
		spy.start();
		verify(spy, times(1)).nextTurn();
	}
	
	@Test
	public void ShouldFailIfLastSquareInPrintBoardIsNotTheLion() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(contains("| Li |\n  ----------------------------------\n"));
	}
	
	@Test
	public void ShouldFailIfTheOutputDoesntShowThatItIsDarkPlayersTurnAtStart() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(contains("DARK Players' turn. Enter square you want to move from: "));
	}
	
	@Test
	public void ShouldFailIfTheSystemDoesntTellUsItIsNotOurPiece() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		ByteArrayInputStream in = new ByteArrayInputStream("1,1".getBytes());
		System.setIn(in);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(contains("That is not your piece."));
	}
	
	@Test
	public void ShouldFailIfEmptySquareMessageIsNotPrinted() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		ByteArrayInputStream in = new ByteArrayInputStream("8,1".getBytes());
		System.setIn(in);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(contains("There is no piece at that square."));
	}
	
	@Test
	public void ShouldFailIfOutputDiffersFomMovedElephant() {
		PrintStream out = mock(PrintStream.class);
		System.setOut(out);
		
		ByteArrayInputStream in = new ByteArrayInputStream("7,1,up".getBytes());
		System.setIn(in);
		
		Game game = new Game();
		game.start();
		
		verify(out).println(contains("Moved DARK ELEPHANT upwards to square 6,1."));
	}

}
