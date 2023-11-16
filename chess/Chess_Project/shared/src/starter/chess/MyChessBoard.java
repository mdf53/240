package chess;

import java.util.HashSet;
import java.util.Set;

public class MyChessBoard implements chess.ChessBoard {
    private int rows = 8;
    private int cols = 8;
    private ChessPiece[][] board;

    public MyChessBoard(){
        board = new ChessPiece[8][8];
    }

    public MyChessBoard(MyChessBoard original) {
        // Create a new instance of MyChessBoard
        this.board = new ChessPiece[original.board.length][original.board[0].length];

        // Copy the pieces from the original board to the new board
        for (int row = 0; row < original.board.length; row++) {
            for (int col = 0; col < original.board[row].length; col++) {
                ChessPiece piece = original.board[row][col];

                if (piece != null) {
                    ChessPiece.PieceType type = piece.getPieceType();
                    ChessGame.TeamColor pieceColor = piece.getTeamColor();
                    if(type == ChessPiece.PieceType.PAWN){
                        this.board[row][col] = new Pawn(pieceColor);
                    } else if (type == ChessPiece.PieceType.BISHOP){
                        this.board[row][col] = new Bishop(pieceColor);
                    }else if (type == ChessPiece.PieceType.KING){
                        this.board[row][col] = new King(pieceColor);
                    }else if (type == ChessPiece.PieceType.KNIGHT){
                        this.board[row][col] = new Knight(pieceColor);
                    }else if (type == ChessPiece.PieceType.ROOK){
                        this.board[row][col] = new Rook(pieceColor);
                    }else if (type == ChessPiece.PieceType.QUEEN){
                        this.board[row][col] = new Queen(pieceColor);
                    }     // Implement a clone method in ChessPiece
                }
            }
        }
    }

    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getColumn()][position.getRow()] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        if(position.getColumn() > 7 || position.getColumn() < 0){
            return null;
        }
        if(position.getRow() > 7 || position.getRow() < 0){
            return null;
        }
        return board[position.getColumn()][position.getRow()];
    }

    private ChessPiece[][] getBoard(){
        return board;
    }

    @Override
    public void resetBoard() {
        board = new ChessPiece[cols][rows];
        //populate pieces in their correct spots.
        //for loop iterating through. If row is 1 or 7 then it's a pawn.
        //if row is 0 or 8 then it's a piece.
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++){
                { //0 and 1 are white, 6 and 7  are black.
                if(r == 0){
                    if(c == 0 || c == 7){
                        board[c][r] = new Rook(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                    } else if (c == 1 || c == 6){
                        //add knights
                        board[c][r] = new Knight(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                    } else if (c == 2 || c == 5){
                        //add bishops
                        board[c][r] = new Bishop(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                    } else if (c == 3){
                        // add queen
                        board[c][r] = new Queen(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                    } else if (c == 4){
                        // add king
                        board[c][r] = new King(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                    }
                }else if(r == 1){
                    board[c][r] = new Pawn(ChessGame.TeamColor.WHITE, new MyChessPosition(c,r));
                } else if (r == 6){
                    board[c][r] = new Pawn(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                }else if (r == 7){
                    if(c == 0 || c == 7){
                        board[c][r] = new Rook(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                    }else if (c == 1 || c == 6){
                        //add knights
                        board[c][r] = new Knight(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                    } else if (c == 2 || c == 5){
                        //add bishops
                        board[c][r] = new Bishop(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                    } else if (c == 3){
                        // add queen
                        board[c][r] = new Queen(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                    } else if (c == 4){
                        // add king
                        board[c][r] = new King(ChessGame.TeamColor.BLACK, new MyChessPosition(c,r));
                    }
                }
            }
        }
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for(int c = cols - 1; c >= 0; c--){
            for(int r = 0; r < rows; r++){
                if(board[r][c] != null) {
                    sb.append(board[r][c].serialize());
                } else{
                    sb.append("_,");
                }
            }
        }
        return sb.toString();    }

    public void clearBoard(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                board[r][c] = null;
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int c = cols - 1; c >= 0; c--){
            for(int r = 0; r < rows; r++){
                if(board[r][c] != null) {
                    sb.append(board[r][c]);
                } else{
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public Set<ChessMove> getWhiteMoves(){
        Set<ChessMove> collection = new HashSet<>();
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                ChessPosition tempP = new MyChessPosition(c+1,r+1);
                ChessPiece temp = getPiece(tempP);
                if(temp != null && temp.getTeamColor() == ChessGame.TeamColor.WHITE){
                    collection.addAll(temp.pieceMoves(this, tempP));
                }
            }
        }
        return collection;
    }
    public Set<ChessMove> getBlackMoves(){
        Set<ChessMove> collection = new HashSet<>();
        for(int c = 8; c >= 0; c--){
            for(int r = 8; r >= 0; r--){
                ChessPosition tempP = new MyChessPosition(c+1,r+1);
                ChessPiece temp = getPiece(tempP);
                if(temp != null && temp.getTeamColor() == ChessGame.TeamColor.BLACK){
                    collection.addAll(temp.pieceMoves(this, tempP));
                }
            }
        }
        return collection;
    }

}
