package chess;

import java.util.Collection;

public abstract class MyChessPiece implements chess.ChessPiece {
    ChessGame.TeamColor color;
    PieceType type;
    ChessPosition position;

    MyChessPiece(ChessGame.TeamColor color, ChessPosition position){
        this.color = color;
        //this.type = type;
        this.position = position;
    }
    MyChessPiece(ChessGame.TeamColor color){
        this.color = color;
    }

    public void setPosition(ChessPosition position){
        this.position = position;
    }

    /**
     * @return Which team this chess piece belongs to
     */
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }


    public ChessPosition getPosition(){
        return this.position;
    }
    /**
     * @return which type of chess piece this piece is
     */
    @Override
    public PieceType getPieceType() {
        return type;
    }

    public void setPromotion(PieceType promotionPiece){
        type = promotionPiece;
    }
    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<MyChessMove> pieceMoves(ChessBoard board, MyChessPosition myPosition){
        return null;
    }
    public Collection<MyChessMove> pieceMoves(ChessBoard board, MyChessPosition myPosition, PieceType promotionPiece){
        return null;
    }


    public int hashCode(){
        int hash = 181;
        hash = hash ^ position.getColumn() ^ position.getRow();
        return hash;
    }

    //implement equals and hashCode.
    public boolean equals(Object o){
        if(this.getClass() != o.getClass()){
            return false;
        }
        MyChessPiece piece = (MyChessPiece)o;
        if(piece.color != this.color){
            return false;
        }
        if(this.getPieceType() != piece.getPieceType()){
            return false;
        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(this.getTeamColor() == ChessGame.TeamColor.WHITE){
            sb.append("(w)");
        }else{
            sb.append("(b)");
        }
        if (this.getPieceType() == PieceType.PAWN) {
            sb.append("P");
        } else if (this.getPieceType() == PieceType.ROOK) {
            sb.append("R");
        }else if (this.getPieceType() == PieceType.BISHOP) {
            sb.append("B");
        }
        else if (this.getPieceType() == PieceType.KNIGHT) {
            sb.append("N");
        }else if (this.getPieceType() == PieceType.KING) {
            sb.append("K");
        }else if (this.getPieceType() == PieceType.QUEEN) {
            sb.append("Q");
        }

        return sb.toString();
    }


}
