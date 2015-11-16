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
		this.value = this.assignValue();
	}
	
	public int getValue() {
		return value;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * This method returns the correct value of this piece on initialization. 
	 * @return Int value
	 */
	private int assignValue() {
		switch (this.animal) {
		case RAT:
			return 1;
		case CAT:
			return 2;
		case WOLF:
			return 3;
		case DOG:
			return 4;
		case LEOPARD:
			return 5;
		case TIGER:
			return 6;
		case LION:
			return 7;
		case ELEPHANT:
			return 8;
		}
		return 0;
	}
}
