package chess;

import java.util.Collection;
import java.util.HashSet;

public class Queen extends MyChessPiece{
    public Queen(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.QUEEN;
    }
    public Queen(ChessGame.TeamColor color){
        super(color);
        type = PieceType.QUEEN;
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(board.getPiece(myPosition) == null){
            return null;
        }
        Collection<ChessMove> collection = new HashSet<>();
        Rook tempRook = new Rook(this.color, myPosition);
        Bishop tempBishop = new Bishop(this.color, myPosition);
        collection.addAll(tempRook.pieceMoves(board, myPosition));
        collection.addAll(tempBishop.pieceMoves(board,myPosition));
        return collection;
    }
    public int hashCode(){
        return super.hashCode();
    }
    public boolean equals(Object o){
        return super.equals(o);
    }
}
