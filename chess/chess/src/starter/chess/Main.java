package chess;

public class Main {

    public static void main(String[] args){
        MyChessBoard board = new MyChessBoard();
        System.out.println(board.toString());
        board.addPiece(new MyChessPosition(3,5),new Pawn(ChessGame.TeamColor.WHITE));
        System.out.println(board.toString());
        //System.out.println(board.getPiece(new MyChessPosition(4,5)));
        board.addPiece(new MyChessPosition(3,5),new Rook(ChessGame.TeamColor.WHITE));
        board.addPiece(new MyChessPosition(3,1),new Rook(ChessGame.TeamColor.WHITE));


        ChessPiece rook;
        rook = new Rook(ChessGame.TeamColor.WHITE);

        board.clearBoard();
        //System.out.println(board);

        ChessPosition pos = new MyChessPosition(2,3);
        board.addPiece(pos, rook);

        System.out.println(board.toString());


    }
}
