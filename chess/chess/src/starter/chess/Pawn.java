package chess;

import chess.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.awt.Color.white;

public class Pawn extends chess.MyChessPiece {

    Pawn(ChessGame.TeamColor color, ChessPosition p) {
        super(color, p);
        type = PieceType.PAWN;
    }
    public Pawn(ChessGame.TeamColor color){
        super(color);
        type = PieceType.PAWN;
    }

    public int hashCode(){
        return super.hashCode();
    }

    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        int numR = 8;
        int numC = 8;
        if(board.getPiece(myPosition) == null){
            return null;
        }
        Collection<ChessMove> collection = new HashSet<>();
        int currentRow = myPosition.getRow();
        int currentCol = myPosition.getColumn();

        if(getTeamColor() == ChessGame.TeamColor.WHITE){
            whiteMoves(board, myPosition, collection, currentCol, currentRow);
        } else{
            blackMoves(board, myPosition, collection, currentCol, currentRow);
        }

        return collection;
    }

    private void blackMoves(ChessBoard board, ChessPosition myPosition, Collection collection, int currentCol, int currentRow) {
        boolean canPromote = false;
        if (currentRow == 1) { //ready for promotion.
            canPromote = true;
        }
        MyChessPosition p1 = new MyChessPosition(currentCol + 1, currentRow); //one column ahead.
        //check to see if the stop in the row above it is occupied or out of bounds. If not that's a valid move.
        if (board.getPiece(p1) == null) {
            if(canPromote){
                addPromotions(new MyChessMove(myPosition, p1), collection);
            } else{
                collection.add(new MyChessMove(myPosition, p1));
            }
            //if has not moved it can move forward two spaces.
            if (currentRow ==  6) {
                //add the 2 spaces in front of it.
                MyChessPosition p2 = new MyChessPosition(currentCol + 1, currentRow - 1);
                if (board.getPiece(p2) == null) {
                    collection.add(new MyChessMove(myPosition, p2));
                }
            }
        }
        if (currentCol == 0) {
            MyChessPosition leftCorner = new MyChessPosition(currentCol, currentRow);
            ChessPiece lc = board.getPiece(leftCorner);
            if(lc != null) {
                if (board.getPiece(leftCorner).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (canPromote) {
                        addPromotions(new MyChessMove(myPosition, leftCorner), collection);
                    } else {
                        collection.add(new MyChessMove(myPosition, leftCorner));
                    }
                }
            }

        } else if (currentCol == 7) {
            MyChessPosition rightCorner = new MyChessPosition(currentCol + 2, currentRow);
            ChessPiece rc = board.getPiece(rightCorner);
            if(rc != null) {
                if (board.getPiece(rightCorner).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (canPromote) {
                        addPromotions(new MyChessMove(myPosition, rightCorner), collection);
                    } else {
                        collection.add(new MyChessMove(myPosition, rightCorner));
                    }
                }
            }
        } else {
            MyChessPosition rightCorner = new MyChessPosition(currentCol + 2, currentRow);
            ChessPiece temp = board.getPiece(rightCorner);
            if (temp != null && temp.getTeamColor() != color) {
                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, rightCorner), collection);
                } else {
                    collection.add(new MyChessMove(myPosition, rightCorner));
                }
            }
            MyChessPosition leftCorner = new MyChessPosition(currentCol, currentRow);
            temp = board.getPiece(leftCorner);
            if (temp != null && temp.getTeamColor() != color) {
                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, leftCorner), collection);
                } else {
                    collection.add(new MyChessMove(myPosition, leftCorner));
                }
            }
        }
    }
    private void whiteMoves(ChessBoard board, ChessPosition myPosition, Collection collection, int currentCol, int currentRow){
        boolean canPromote = false;
        if (currentRow == 6) { //ready for promotion.
            canPromote = true;
        }
        MyChessPosition p1 = new MyChessPosition(currentCol+1,currentRow+2); //one column ahead.
        //check to see if the stop in the row above it is occupied or out of bounds. If not that's a valid move.
        if(board.getPiece(p1) == null){
            if(canPromote){
                addPromotions(new MyChessMove(myPosition, p1), collection);
            } else{
                collection.add(new MyChessMove(myPosition, p1));
            }
            //if has not moved it can move forward two spaces.
            if(currentRow == 1 && color == ChessGame.TeamColor.WHITE){
                //add the 2 spaces in front of it.
                MyChessPosition p2 = new MyChessPosition(currentCol+1,currentRow+3);
                if(board.getPiece(p2) == null){
                    collection.add(new MyChessMove((MyChessPosition) myPosition, p2));
                }
            }
        }
        if(currentCol == 7) {
            MyChessPosition leftCorner = new MyChessPosition(currentCol, currentRow + 2);
            if(board.getPiece(leftCorner) != null && board.getPiece(leftCorner).getTeamColor() == ChessGame.TeamColor.BLACK){
                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, leftCorner), collection);
                }else {
                    collection.add(new MyChessMove(myPosition, leftCorner));
                }            }

        } else if(currentCol == 0){
            MyChessPosition rightCorner = new MyChessPosition(currentCol + 2, currentRow + 2);
            if(board.getPiece(rightCorner) != null && board.getPiece(rightCorner).getTeamColor() == ChessGame.TeamColor.BLACK){
                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, rightCorner), collection);
                }else {
                    collection.add(new MyChessMove(myPosition, rightCorner));
                }            }
        }
        else{
            MyChessPosition rightCorner = new MyChessPosition(currentCol + 2, currentRow + 2);
            ChessPiece temp = board.getPiece(rightCorner);
            if (temp != null && temp.getTeamColor() != color) {                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, rightCorner), collection);
                }else {
                    collection.add(new MyChessMove(myPosition, rightCorner));
                }            }
            MyChessPosition leftCorner = new MyChessPosition(currentCol, currentRow + 2);
            ChessPiece lc = board.getPiece(leftCorner);
            if(lc != null &&   lc.getTeamColor() != color){
                if(canPromote){
                    addPromotions(new MyChessMove(myPosition, leftCorner), collection);
                }else {
                    collection.add(new MyChessMove(myPosition, leftCorner));
                }            }
        }

    }

    void addPromotions(ChessMove move, Collection<ChessMove> collection){
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        for(int i = 0; i < 4; i++){
            ChessMove temp = null;
            if( i == 0){
                temp = new MyChessMove(start, end, PieceType.QUEEN);
            } else if (i == 1){
                temp = new MyChessMove(start, end, PieceType.ROOK);
            } else if (i == 2){
                temp = new MyChessMove(start, end, PieceType.KNIGHT);
            } else if (i == 3){
                temp = new MyChessMove(start, end, PieceType.BISHOP);
            }
            collection.add(temp);
        }
    }

}
