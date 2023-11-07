package services;

import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.Game;
import requests.CreateGameRequest;
import results.CreateGameResult;

/**
 * Service for Create Game API
 */
public class CreateGameService {
    /**
     * Creates a game
     * @param request is the request info
     * @param authToken is the header token
     * @return the result
     */
    public CreateGameResult createGame(CreateGameRequest request, String authToken){

        CreateGameResult result = new CreateGameResult();
        try {

            Game game = new Game(request.getGameName());
            GameDAO.createGame(game, authToken);
            result.setGameID(game.getGameID());
        }
        catch(DataAccessException exception){
            result.setMessage(exception.getMessage());
        }
        return result;
    }
}
