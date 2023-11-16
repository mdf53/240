package chess;

public class MyChessPosition implements chess.ChessPosition {
    int row;
    int column;


    public MyChessPosition(int c, int r){
        row = r - 1;
        column = c - 1;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode(){
        int hash = 1;
        hash = hash ^ row ^ column;
        return hash;
    }
    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        MyChessPosition pos = (MyChessPosition)o;
        if(pos.column != this.column){
            return false;
        }
        if(pos.row != this.row){
            return false;
        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("R: ");
        sb.append(row);
        sb.append(", C: ");
        sb.append(column);
        return sb.toString();
    }

    //Add equals and hashCode functions

}
