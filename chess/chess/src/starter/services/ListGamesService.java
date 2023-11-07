package services;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.DataAccessException;
import results.ListGamesResults;
import results.LogoutResult;

import java.sql.SQLException;

/**
 * Service for List Games API
 */
public class ListGamesService {
    /**
     * Lists Games
     * @param authToken string for auth token from header
     * @return Results aka list of games
     */
    public ListGamesResults listGames(String authToken) {
    //make a ListGamesResult for each game or just the game associated with the authToken?
        ListGamesResults result = new ListGamesResults(null);
        try {
            if(AuthDAO.invalidToken(authToken)){
                result.setMessage("Error: unauthorized");
            }
            result.setGameList(GameDAO.getAllGames());
        } catch(DataAccessException | SQLException ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }
}
