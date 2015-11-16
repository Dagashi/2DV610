package douShouQiChess;

public class Piece {
	private int value;
	private Animal animal;
	
	public enum Animal {
		RAT, CAT, WOLF, DOG,
		LEOPARD, TIGER, LION, ELEPHANT 
	}
	
	public Piece(Animal animal) {
		this.animal = animal;
	}

	public int getValue() {
		return value;
	}
}
