package results;

import requests.CreateGameRequest;

/**
 * Result for Create Game API
 */
public class CreateGameResult {
    /**
     * error message
     */
    private String message;
    private Integer gameID;

    /**
     * Constructor w/ message = error.
     * @param m error message.
     */
    public CreateGameResult(String m){
        message = m;
        gameID = null;
    }


    /**
     * Constructor w/out message = success
     */
    public CreateGameResult(){
        message = null;
    }

    public String getMessage(){
        return message;
    }
    public Integer getGameID() {return gameID;}

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }
}
