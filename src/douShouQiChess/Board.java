package douShouQiChess;

import java.util.HashMap;

public class Board {
	public final HashMap<Square, Piece> positions;

	public Board() {
		positions = new HashMap<Square, Piece>();
	}

	public void setPieceAt(Square square, Piece piece) {
		positions.put(square, piece);
	}

}
