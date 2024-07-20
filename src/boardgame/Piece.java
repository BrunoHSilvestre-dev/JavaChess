package boardgame;

public abstract class Piece {
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	protected Board getBoard() {
		return board;
	}

	public abstract Piece possibleMoves();
	public abstract Boolean possibleMove(Position position);
	public abstract Boolean isThereAnyPossibleMove();
}
