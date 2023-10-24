package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Rook extends chess.MyChessPiece{
    private boolean hasMoved;

    Rook(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.ROOK;
    }
    public Rook(ChessGame.TeamColor color){
        super(color);
        type = PieceType.ROOK;
    }

    @Override
    public Set<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int numR = 8; //number of rows
        int numC = 8; //number of columns

        int rowNum = myPosition.getRow();
        int colNum = myPosition.getColumn();

        Set<ChessMove> collection = new HashSet<>();
        if(board.getPiece(myPosition) == null){
            return collection;
        }
        //check along its entire row and column. It can move until it hits an edge, a member of it's team
        //or if it captures a member of the opposite team.
        for(int col = colNum  + 1; col < numC; col++){ //go to up
            ChessPiece temp = board.getPiece(new MyChessPosition(col + 1,rowNum + 1));
            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(col + 1,rowNum + 1)));
            } else if(temp.getTeamColor() != this.getTeamColor()){ //add it so that it can attack.
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(col + 1,rowNum + 1)));
                break;
            }
            else{
                break;
            }
        }
        //now check vertical options.
        for(int col = colNum - 1; col >= 0; col--){ //go to down.
            ChessPiece temp = board.getPiece(new MyChessPosition(col + 1,rowNum + 1));
            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(col + 1,rowNum + 1)));
            } else if(temp.getTeamColor() != this.getTeamColor()){ //add it so that it can attack.
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(col + 1,rowNum + 1)));
                break;
            }
            else{
                break;
            }
        }
        for(int row = rowNum - 1; row >= 0; row--){ //go to left of it.
            ChessPiece temp = board.getPiece(new MyChessPosition(colNum+1,row+1));
            if(temp == null){ //empty square, free to move there.
                collection.add(new MyChessMove(myPosition, new MyChessPosition(colNum + 1,row + 1)));
            } else if(temp.getTeamColor() != this.getTeamColor()){ //enemy piece, can attack but can't go passed
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(colNum + 1,row + 1)));
                break;
            }
            else{ //member of own team, cannot continue
                break;
            }
        }
        for(int row = rowNum + 1; row < numR; row++){ //go to right of it
            ChessPiece temp = board.getPiece(new MyChessPosition(colNum + 1,row + 1));
            if(temp == null){
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(colNum + 1,row + 1)));
            } else if(temp.getTeamColor() != this.getTeamColor()){ //add it so that it can attack.
                collection.add(new MyChessMove((MyChessPosition) myPosition, new MyChessPosition(colNum + 1,row + 1)));
                break;
            }
            else{
                break;
            }
        }




        //eventually implement castle.

        return collection;
    }

    public int hashCode(){
        return super.hashCode();
    }
    public boolean equals(Object o){
        return super.equals(o);
    }

}
