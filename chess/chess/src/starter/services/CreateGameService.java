package services;

import requests.CreateGameRequest;
import results.CreateGameResult;

/**
 * Service for Create Game API
 */
public class CreateGameService {
    /**
     * Creates a game
     * @param request is the request info
     * @param authtoken is the header token
     * @return the result
     */
    CreateGameResult createGame(CreateGameRequest request, String authtoken){

        return request.createGame(authtoken);
    }
}
