package chess;

import java.util.Collection;
import java.util.HashSet;

public class King extends MyChessPiece{
    public King(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.KING;
    }
    public King(ChessGame.TeamColor color){
        super(color);
        type = PieceType.KING;
    }
    public int hashCode(){
        return super.hashCode();
    }
    public boolean equals(Object o){
        return super.equals(o);
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> collection = new HashSet<>();
        int c = myPosition.getColumn();
        int r = myPosition.getRow();
        //check the 8 squares around the king to see if they are available.
        //left
        ChessPosition p = new MyChessPosition(c + 1,r);
        addMoves(board, myPosition, p, collection);
        //top left
        p = new MyChessPosition(c+2,r);
        addMoves(board, myPosition, p, collection);
        //above
        p = new MyChessPosition(c+2,r+1);
        addMoves(board, myPosition, p, collection);
        //top right
        p = new MyChessPosition(c+2,r+2);
        addMoves(board, myPosition, p, collection);
        //right
        p = new MyChessPosition(c+1,r+2);
        addMoves(board, myPosition, p, collection);
        //bottom right
        p = new MyChessPosition(c,r+2);
        addMoves(board, myPosition, p, collection);
        //below
        p = new MyChessPosition(c,r+1);
        addMoves(board, myPosition, p, collection);
        //bottom left
        p = new MyChessPosition(c,r);
        addMoves(board, myPosition, p, collection);

        return collection;
    }
    private void addMoves(ChessBoard board, ChessPosition start, ChessPosition end, Collection<ChessMove> collection){
        if(end.getColumn() > 7 || end.getColumn() < 0){
            return;
        }
        if(end.getRow() > 7 || end.getRow() < 0){
            return;
        }
        ChessPiece temp = board.getPiece(end);

        if(temp == null){
            collection.add(new MyChessMove(start, end));
        }
        else if(temp.getTeamColor() != this.getTeamColor()){
            collection.add(new MyChessMove(start, end));
        }
    }

}
