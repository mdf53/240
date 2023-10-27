package requests;
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
    private String playerColor;
    /**
     * game ID
     */
    private String gameID;

    /**
     * Constructor that initialized variables
     * @param color is the playerColor
     * @param id is the gameID
     */
    public JoinGameRequest(String color, String id){
        playerColor = color;
        gameID = id;
    }

    public String getGameID(){
        return gameID;
    }
    public String getPlayerColor(){
        return playerColor;
    }
}
