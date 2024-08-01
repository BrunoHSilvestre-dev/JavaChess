package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		int colorDirectionMultiplier = (getColor() == Color.WHITE) ? -1 : 1;
		
		p.setValues(position.getRow() + colorDirectionMultiplier, position.getColumn());
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + colorDirectionMultiplier, position.getColumn() - 1);
		if (getBoard().positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + colorDirectionMultiplier, position.getColumn() + 1);
		if (getBoard().positionExists(p) && isThereOponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		if (getMoveCount() == 0) {
			p.setValues(position.getRow() + (2 * colorDirectionMultiplier), position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && mat[p.getRow() - colorDirectionMultiplier][p.getColumn()]) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
				
		return mat;
	}

}
