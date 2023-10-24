package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class MyChessGame implements chess.ChessGame {
    private ChessBoard board;
    private TeamColor whoseTurn;
    public MyChessGame(){
        whoseTurn = TeamColor.WHITE;
        board = new MyChessBoard();
    }
    /**
     * @return Which team's turn it is
     */
    @Override
    public TeamColor getTeamTurn() {
        return whoseTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */

    @Override
    public void setTeamTurn(TeamColor team) {
        if(team == TeamColor.WHITE){
            whoseTurn = TeamColor.WHITE;
        } else {
            whoseTurn = TeamColor.BLACK;
        }
    }


    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param /startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     *         startPosition
     */
    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        //check to see if the king starts in check, if only those moves that make it out of check count.
        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> moves = piece.pieceMoves(board, startPosition);
        if(isInCheck(piece.getTeamColor())){
            Collection<ChessMove> newMoves = new HashSet<>();
            for (ChessMove move : moves) {
                ChessBoard tempB = new MyChessBoard((MyChessBoard) board);
                tempB.addPiece(move.getEndPosition(), piece);
                tempB.addPiece(move.getStartPosition(), null);
                if (!isInCheck(piece.getTeamColor(), tempB)) {
                    newMoves.add(move);
                }
            }
            return newMoves;
        }else {
            Collection<ChessMove> newMoves = new HashSet<>(moves);
            for (ChessMove move : moves) {
                ChessBoard tempB = new MyChessBoard((MyChessBoard) board);
                tempB.addPiece(move.getEndPosition(), piece);
                tempB.addPiece(move.getStartPosition(), null);
                if (isInCheck(piece.getTeamColor(), tempB)) {
                    newMoves.remove(move);
                }
            }
            return newMoves;
        }
    }


    /**
     * Makes a move in a chess game
     *
     * @param /move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition start = move.getStartPosition();
        ChessPiece piece = board.getPiece(start);
        Collection<ChessMove> validMoves = piece.pieceMoves(board, start);

        if(!validMoves.contains(move) || piece.getTeamColor() != whoseTurn){
            throw new InvalidMoveException();
        }
        if(isInCheck(whoseTurn)){
            ChessBoard tempB = new MyChessBoard((MyChessBoard) board);
            tempB.addPiece(move.getEndPosition(), piece);
            tempB.addPiece(start, null);
            if(isInCheck(whoseTurn, tempB)){
                throw new InvalidMoveException();
            }
        }
        if(move.getPromotionPiece() != null && move.getPromotionPiece() != piece.getPieceType()){
            piece.setPromotion(move.getPromotionPiece());
        }
        piece.setPosition(move.getEndPosition());
        board.addPiece(move.getEndPosition(),piece);
        board.addPiece(move.getStartPosition(), null);
        if(whoseTurn == TeamColor.WHITE){
            setTeamTurn(TeamColor.BLACK);
        } else{
            setTeamTurn(TeamColor.WHITE);
        }
    }





    /**
     * Determines if the given team is in check
     *
     * @param /teamColor which team to check for check
     * @return True if the specified team is in check
     */
    @Override
    public boolean isInCheck(TeamColor teamColor) {
        //check all of the moves of the opponent's pieces
        //find a way to iterate through the pieces of just one color.
        Set<ChessMove> collection = new HashSet<>();
        ChessPosition reyP = findKing(teamColor);
        if(teamColor == TeamColor.WHITE){
            collection = board.getBlackMoves();
        } else{
            collection = board.getWhiteMoves();
        }

        for(ChessMove move: collection){
            if(move.getEndPosition().equals(reyP)){
                return true;
            }
        }

        //if any of them includes the king of teamColor return true.
        return false;
    }

    public boolean isInCheck(TeamColor teamColor, ChessBoard board1) {
        //check all of the moves of the opponent's pieces
        //find a way to iterate through the pieces of just one color.
        Set<ChessMove> collection = new HashSet<>();
        ChessPosition reyP = findKing(teamColor, board1);
        if(teamColor == TeamColor.WHITE){
            collection = board1.getBlackMoves();
        } else{
            collection = board1.getWhiteMoves();
        }

        for(ChessMove move: collection){
            if(move.getEndPosition().equals(reyP)){
                return true;
            }
        }

        //if any of them includes the king of teamColor return true.
        return false;
    }
            public boolean isInCheck(TeamColor teamColor, ChessPosition reyP, ChessBoard b) {
            //check all of the moves of the opponent's pieces
            //find a way to iterate through the pieces of just one color.
            Set<ChessMove> collection = new HashSet<>();
            if(teamColor == TeamColor.WHITE){
                collection = b.getBlackMoves();
            } else{
                collection = b.getWhiteMoves();
            }

            for(ChessMove move: collection){
                if(move.getEndPosition().equals(reyP)){
                    return true;
                }
            }

        //if any of them includes the king of teamColor return true.
        return false;
    }

    private ChessPosition findKing(TeamColor teamColor){
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                ChessPosition tempP = new MyChessPosition(c+1,r+1);
                ChessPiece temp = board.getPiece(tempP);
                if(temp != null && temp.getTeamColor() == teamColor && temp.getPieceType() == ChessPiece.PieceType.KING){
                    return tempP;
                }
            }
        }
        return null;
    }

    private ChessPosition findKing(TeamColor teamColor, ChessBoard board1){
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                ChessPosition tempP = new MyChessPosition(c+1,r+1);
                ChessPiece temp = board1.getPiece(tempP);
                if(temp != null && temp.getTeamColor() == teamColor && temp.getPieceType() == ChessPiece.PieceType.KING){
                    return tempP;
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        //if in check, see if the king has any available moves
        //if yes, if he moved there would he still be in check
        if(!isInCheck(teamColor)){
            return false;
        }
        //get the king's possible moves.
        ChessPosition kingPos = findKing(teamColor);
        ChessPiece king = board.getPiece(kingPos);
        Collection<ChessMove> kingMoves = king.pieceMoves(board, kingPos);
        //see if all of his moves are still blocked.
        for(ChessMove move: kingMoves){
            ChessPosition endPos = move.getEndPosition();
            ChessBoard tempBoard =  new MyChessBoard((MyChessBoard) board);
            tempBoard.addPiece(kingPos, null);
            tempBoard.addPiece(endPos, king);
            if(!isInCheck(teamColor,endPos,tempBoard)){
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)){
            return false;
        }
        ChessPosition kingPos = findKing(teamColor);
        ChessPiece king = board.getPiece(kingPos);
        Collection<ChessMove> kingMoves = king.pieceMoves(board, kingPos);
        //see if all of his moves are still blocked.
        for(ChessMove move: kingMoves){
            ChessPosition endPos = move.getEndPosition();
            ChessBoard tempBoard =  new MyChessBoard((MyChessBoard) board);
            tempBoard.addPiece(kingPos, null);
            tempBoard.addPiece(endPos, king);
            if(!isInCheck(teamColor,endPos,tempBoard)){
                return false;
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    @Override
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    @Override
    public ChessBoard getBoard() {
        return board;
    }
}
