package chess;

public class MyChessMove implements chess.ChessMove {

    private ChessPosition start;
    private ChessPosition end;
    private ChessPiece.PieceType promotionPiece;
    public MyChessMove(ChessPosition s, ChessPosition e){
        start  = s;
        end = e;
        this.promotionPiece = null;
    }
    public MyChessMove(ChessPosition s, ChessPosition e, ChessPiece.PieceType promotionPiece){
        start  = s;
        end = e;
        this.promotionPiece = promotionPiece;
    }

    public void setPromotionPiece(ChessPiece.PieceType promotionP){
        this.promotionPiece = promotionP;
    }

    public int hashCode(){
        int hash = 11;
        hash = hash ^ start.getColumn() ^ end.getRow();
        hash = hash ^ start.getRow() ^ end.getColumn();
        return hash;
    }

    public boolean equals(Object o){
        if(o.getClass() != this.getClass()){
            return false;
        }
        MyChessMove cm = (MyChessMove) o;
        if(!this.start.equals(cm.start)){
            return false;
        }
        if(!this.end.equals(cm.end)){
            return false;
        }
        if(this.promotionPiece == null && cm.promotionPiece != null || this.promotionPiece != null && cm.promotionPiece == null){
            return false;
        }
        if(this.promotionPiece != null){
            if(!this.promotionPiece.equals(cm.promotionPiece)){
                return false;
            }
        }
        return true;
    }


    @Override
    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return start;
    }

    /**
     * @return ChessPosition of ending location
     */
    @Override
    public ChessPosition getEndPosition() {
        return end;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        //implement me!
        return promotionPiece;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Start: ");
        sb.append(start);
        sb.append("\nEnd: ");
        sb.append(end);
        sb.append("\n Promotion: ");
        sb.append(promotionPiece);
        return sb.toString();
    }

    //Add equals and hashCode functions

}
