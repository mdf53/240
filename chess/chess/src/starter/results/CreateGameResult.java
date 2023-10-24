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

    /**
     * Constructor w/ message = error.
     * @param m error message.
     */
    public CreateGameResult(String m){
        message = m;
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

}
