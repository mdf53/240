package services;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.User;
import results.JoinGameResult;
import requests.JoinGameRequest;

import java.sql.SQLException;

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
    public JoinGameResult joinGame(JoinGameRequest request, String authToken) {
        String gamename = request.getGameID();
        String playerColor = request.getPlayerColor();
        JoinGameResult result = new JoinGameResult(null);

        try {
            if (!AuthDAO.invalidToken(authToken)){
                result.setMessage("Error: unauthorized");
                return result;
            }
//            else if(!GameDAO.gameExists(gamename)){
//                result.setMessage("Error: bad request");
//                return result;
//            }
            GameDAO.joinGame(gamename, playerColor, authToken);
            result.setGameID(gamename);
            result.setPlayerColor(playerColor);
        }catch(DataAccessException | SQLException ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }
}
