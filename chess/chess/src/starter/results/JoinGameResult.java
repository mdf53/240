package results;

/**
 * Result for Join Game API
 */
public class JoinGameResult {
    /**
     * ID of game
     */
    private String gameID;
    /**
     * player color
     */
    private String playerColor;
    /**
     * error message
     */
    private String message;
    /**
     * if they are an observor or not
     */

    /**
     * Successful constructor
     * @param color is the player color to be set
     * @param ID is the gameID to be set
     */
    public JoinGameResult(String color, String ID){
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
        gameID = null;
    }
    public String getGameID(){
        return gameID;
    }
    public String getMessage(){
        return message;
    }
    public String getPlayerColor(){
        return playerColor;
    }


}
