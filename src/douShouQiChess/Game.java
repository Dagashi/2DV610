package douShouQiChess;

import java.util.HashMap;

import douShouQiChess.Piece.Animal;
import douShouQiChess.Piece.Color;

public class Game {
	private Board board;
	public HashMap<Color, Player> players;
	
	/**
	 * Constructor
	 */
	public Game() {
		players = new HashMap<Color, Player>();
	}
	
	/**
	 * This method starts a new game of Dou Shou Qi chess.
	 */
	public void start() {
		initialize(new Board());
		players.put(Color.LIGHT, new Player());
		players.put(Color.DARK, new Player());
	}
	
	/**
	 * This method initializes a board to start the game.
	 * @param Board b
	 */
	public void initialize(Board b) {
		this.board = b;
		
		//Sets out all the LIGHT PIECEs on the board.
		board.setPieceAt(new Square(1,1), new Piece(Animal.LION,Color.LIGHT));
		board.setPieceAt(new Square(1,7), new Piece(Animal.TIGER,Color.LIGHT));
		board.setPieceAt(new Square(2,2), new Piece(Animal.DOG,Color.LIGHT));
		board.setPieceAt(new Square(2,6), new Piece(Animal.CAT,Color.LIGHT));
		board.setPieceAt(new Square(3,1), new Piece(Animal.RAT,Color.LIGHT));
		board.setPieceAt(new Square(3,3), new Piece(Animal.LEOPARD,Color.LIGHT));
		board.setPieceAt(new Square(3,5), new Piece(Animal.WOLF,Color.LIGHT));
		board.setPieceAt(new Square(3,7), new Piece(Animal.ELEPHANT,Color.LIGHT));
		
		//Sets out all the DARK PIECEs on the board.
		board.setPieceAt(new Square(9,7), new Piece(Animal.LION,Color.DARK));
		board.setPieceAt(new Square(9,1), new Piece(Animal.TIGER,Color.DARK));
		board.setPieceAt(new Square(8,6), new Piece(Animal.DOG,Color.DARK));
		board.setPieceAt(new Square(8,2), new Piece(Animal.CAT,Color.DARK));
		board.setPieceAt(new Square(7,7), new Piece(Animal.RAT,Color.DARK));
		board.setPieceAt(new Square(7,5), new Piece(Animal.LEOPARD,Color.DARK));
		board.setPieceAt(new Square(7,3), new Piece(Animal.WOLF,Color.DARK));
		board.setPieceAt(new Square(7,1), new Piece(Animal.ELEPHANT,Color.DARK));
	}
	
}
