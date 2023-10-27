package requests;

import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.Game;
import results.CreateGameResult;

/**
 * Request for Create Game API
 */
public class CreateGameRequest {
    /**
     * name of game
     */
    private String gameName;

    /**
     * Constructor that initialzies name
     * @param name is gameName
     */
    public CreateGameRequest(String name) throws DataAccessException {
        gameName = name;
    }

    public String getGameName(){
        return gameName;
    }

}
