package results;

import chess.ChessGame;

/**
 * Result for Join Game API
 */
public class JoinGameResult {
    /**
     * ID of game
     */
    private int gameID;
    /**
     * player color
     */
    private ChessGame.TeamColor playerColor;
    /**
     * error message
     */
    private String message;
    /**
     * if they are an observor or not
     */

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Successful constructor
     * @param color is the player color to be set
     * @param ID is the gameID to be set
     */
    public JoinGameResult(ChessGame.TeamColor color, int ID){
        playerColor = color;
        gameID = ID;
        message = null;
    }

    /**
     * Failure constructor
     * @param m failure message
     */
    public JoinGameResult(String m){
        message = m;
        playerColor = null;
        gameID = 0;
    }
    public int getGameID(){
        return gameID;
    }
    public String getMessage(){
        return message;
    }
    public ChessGame.TeamColor getPlayerColor(){
        return playerColor;
    }


}
