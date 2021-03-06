package douShouQiChess;

public class Piece {
	private int value;
	private Animal animal;
	private Color color;
	
	public enum Animal {
		RAT, CAT, WOLF, DOG,
		LEOPARD, TIGER, LION, ELEPHANT 
	}
	
	public enum Color {
		DARK, LIGHT
	}
	
	public Piece(Animal animal, Color color) {
		this.animal = animal;
		this.value = this.assignValue();
		this.color = color;
	}
	
	/**
	 * @return Integer this.value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return Animal this.animal
	 */
	public Animal getAnimal() {
		return animal;
	}
	
	/**
	 * @return Color this.color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * This method returns the correct value of this piece on initialization. 
	 * @return integer value
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
