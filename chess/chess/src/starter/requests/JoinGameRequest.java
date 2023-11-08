package requests;
import chess.ChessGame;
import dataAccess.DataAccessException;
import models.Game;
import dataAccess.GameDAO;
import results.JoinGameResult;

/**
 * Request for Join Game API
 */
public class JoinGameRequest {
    /**
     * player color
     */
    private ChessGame.TeamColor playerColor;
    /**
     * game ID
     */
    private int gameID;

    /**
     * Constructor that initialized variables
     * @param color is the playerColor
     * @param id is the gameID
     */
    public JoinGameRequest(ChessGame.TeamColor color, int id){
        playerColor = color;
        gameID = id;
    }

    public int getGameID(){
        return gameID;
    }
    public ChessGame.TeamColor getPlayerColor(){
        return playerColor;
    }
    public void setPlayerColor(ChessGame.TeamColor color){
        playerColor = color;
    }
}
