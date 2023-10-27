package services;
import dataAccess.DataAccessException;
import results.JoinGameResult;
import requests.JoinGameRequest;
/**
 * Service for Join Game API
 */
public class JoinGameService {
    /**
     * Joins a game
     * @param request for join game
     * @param authToken from header
     * @return results
     */
    public JoinGameResult joinGame(JoinGameRequest request, String authToken) throws DataAccessException {
        return request.joinGame(authToken);
    }
}
