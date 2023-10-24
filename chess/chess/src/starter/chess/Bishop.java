package chess;

import java.util.Collection;
import java.util.HashSet;

public class Bishop extends MyChessPiece{
    public Bishop(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.BISHOP;
    }
    public Bishop(ChessGame.TeamColor color){
        super(color);
        type = PieceType.BISHOP;
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //travels along diagonals.
        int numCol = 8;
        int numRow = 8;
        if(board.getPiece(myPosition) == null){
            return null;
        }
        int startRow = myPosition.getRow();
        int startCol = myPosition.getColumn();
        Collection<ChessMove> collection = new HashSet<>();

        //work from the piece into the four directions, adding viable moves as you go.
        //Once you hit a non-viable move, break.
        int r = startRow + 1;
        int c = startCol + 1;
        while(r < numRow && c < numCol){ //up right
            ChessPiece temp = board.getPiece(new MyChessPosition(c + 1,r + 1));
            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
            }
            else if(temp.getTeamColor() != color){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
                break;
            }
            else{
                break;
            }
            r++;
            c++;
        }
        r = startRow + 1;
        c = startCol - 1;
        while(r < numRow && c >= 0){ //down right
            ChessPiece temp = board.getPiece(new MyChessPosition(c + 1,r + 1));

            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
            }
            else if(temp.getTeamColor() != color){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
                break;
            }
            else{
                break;
            }
            r++;
            c--;
        }
        r = startRow - 1;
        c = startCol - 1;
        while(r >= 0 && c >= 0){ //down left
            ChessPiece temp = board.getPiece(new MyChessPosition(c + 1,r + 1));

            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
            }
            else if(temp.getTeamColor() != color){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
                break;
            }
            else{
                break;
            }
            r--;
            c--;
        }
        r = startRow - 1;
        c = startCol + 1;
        while(r >= 0 && c < numCol){ //up left
            ChessPiece temp = board.getPiece(new MyChessPosition(c + 1,r + 1));

            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
            }
            else if(temp.getTeamColor() != color){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(c + 1,r + 1)));
                break;
            }
            else{
                break;
            }
            r--;
            c++;
        }
        return collection;
    }
    public int hashCode(){
        return super.hashCode();
    }
    public boolean equals(Object o){
        return super.equals(o);
    }
}
