package douShouQiChess;

import java.util.HashMap;
import java.util.Scanner;

import douShouQiChess.Piece.Animal;
import douShouQiChess.Piece.Color;

public class Game {
	private Board board;
	public HashMap<Color, Player> players;
	private Color whosTurn;
	
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
		whosTurn = Color.DARK;
		
		System.out.println("Welcome to Dou Shou Qi Chess!");
		
		nextTurn();
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
	
	/**
	 * This method contains the logic for each turn.
	 */
	@SuppressWarnings("resource")
	public void nextTurn() {
		//Start the turn by printing the board.
		board.printBoard();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println(whosTurn.toString()+" Players' turn. Enter square you want to move from: ");
		
		String userMovePiece = scan.next();
		String[] coordinates = userMovePiece.split("\\,");
		Square userMoveFrom = new Square(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
		if(board.isSquareEmpty(userMoveFrom)) {
			System.out.println("There is no piece at that square.");
		}
		else if(board.getPieceAt(userMoveFrom).getColor() != whosTurn) {
			System.out.println("That is not your piece.");
		}
		else {
			//If move-direction been given.
			if(coordinates.length == 3) {
				//If they want to move up.
				if(coordinates[2].equals("up")) {
					//If the move is successful print message.
					if(board.tryMovePiece(userMoveFrom, userMoveFrom.stepUp())) {
						Piece piece = board.getPieceAt(userMoveFrom.stepUp());
						System.out.println("Moved "+piece.getColor().toString()+" "+piece.getAnimal().toString()+" upwards to square "+userMoveFrom.stepUp().getRow()+","+userMoveFrom.stepUp().getColumn()+".");
					}
					
				}
				//If they want to move down.
				else if(coordinates[2].equals("down")) {
					//If the move is successful print message.
					if(board.tryMovePiece(userMoveFrom, userMoveFrom.stepDown())) {
						Piece piece = board.getPieceAt(userMoveFrom.stepDown());
						System.out.println("Moved "+piece.getColor().toString()+" "+piece.getAnimal().toString()+" downwards to square "+userMoveFrom.stepDown().getRow()+","+userMoveFrom.stepDown().getColumn()+".");
					}
					
				}
				//If they want to move right.
				else if(coordinates[2].equals("right")) {
					Square moveTo = userMoveFrom.stepRight();
					if(moveTo != null) {
						//If the move is successful print message.
						if(board.tryMovePiece(userMoveFrom, moveTo)) {
							Piece piece = board.getPieceAt(moveTo);
							System.out.println("Moved "+piece.getColor().toString()+" "+piece.getAnimal().toString()+" right to square "+moveTo.getRow()+","+moveTo.getColumn()+".");
						}
					}
					else {
						printCouldNotMove();
					}
					
				}
				//If they want to move left.
				else if(coordinates[2].equals("left")) {
					Square moveTo = userMoveFrom.stepLeft();
					if(moveTo != null) {
						//If the move is successful print message.
						if(board.tryMovePiece(userMoveFrom, moveTo)) {
							Piece piece = board.getPieceAt(moveTo);
							System.out.println("Moved "+piece.getColor().toString()+" "+piece.getAnimal().toString()+" left to square "+moveTo.getRow()+","+moveTo.getColumn()+".");
						}
					}
					else {
						printCouldNotMove();
					}
				}
			}
		}
		
	}
	
	private void printCouldNotMove() {
		System.out.println("The piece can not move there.");
	}
	
}
