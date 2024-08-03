package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
		
		// special move en passant
		if (position.getRow() == ((getColor() == Color.WHITE) ? 3 : 4)) {
			Position left = new Position(position.getRow(), position.getColumn() - 1);
			if (getBoard().positionExists(left) && isThereOponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
				mat[left.getRow() + colorDirectionMultiplier][left.getColumn()] = true;
			}
			
			Position right = new Position(position.getRow(), position.getColumn() + 1);
			if (getBoard().positionExists(right) && isThereOponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
				mat[right.getRow() + colorDirectionMultiplier][right.getColumn()] = true;
			}
		}
		
		//special move promotion
				
		return mat;
	}

}
