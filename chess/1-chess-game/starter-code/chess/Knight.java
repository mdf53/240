package chess;

import java.util.Collection;
import java.util.HashSet;

public class Knight extends MyChessPiece{
    public Knight(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.KNIGHT;
    }
    public Knight(ChessGame.TeamColor color){
        super(color);
        type = PieceType.KNIGHT;
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
        int col = myPosition.getColumn();
        int row = myPosition.getRow();
        //check the 8 possible options of a knight's movement. Make sure to check to see if it's out of bounds.
        ChessPosition endPos = new MyChessPosition(col + 3, row + 2); //top upper right
        ChessMove move = checkMove(board, myPosition, endPos); //in test needed. Worked
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col + 2, row + 3); //bottom upper right
        move = checkMove(board, myPosition, endPos); //in test needed. Worked
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col, row + 3); //upper top left
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col - 1, row + 2); //lower top left
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col, row - 1); //lower bottom left
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }endPos = new MyChessPosition(col - 1, row); //upper bottom left
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col + 2, row - 1); //lower bottom right
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }
        endPos = new MyChessPosition(col + 3, row); //upper bottom right
        move = checkMove(board, myPosition, endPos);
        if(move != null){
            collection.add(move);
        }
        return collection;
    }

    private ChessMove checkMove(ChessBoard board, ChessPosition start, ChessPosition endPosition){
        if(endPosition.getColumn() > 7 || endPosition.getColumn() < 0){
            return null;
        }
        if(endPosition.getRow() > 7 || endPosition.getRow() < 0){
            return null;
        }
        //check to see if position is occupied
        ChessPiece temp = board.getPiece(endPosition);
        if(temp == null){
            return new MyChessMove(start, endPosition);
        } else if (temp.getTeamColor() != this.getTeamColor()){
            return new MyChessMove(start, endPosition);
        }
        return null;
    }
}
