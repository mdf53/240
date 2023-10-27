package services;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import results.LogoutResult;

/**
 * Service for Logout API
 */
public class LogoutService {

    /**
     * Logs out User
     * @param authToken user to logout
     * @return Results
     */
    public LogoutResult logout(String authToken) throws DataAccessException {
        LogoutResult result = new LogoutResult();
        try {
            AuthDAO.removeToken(authToken);
        } catch(DataAccessException ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }
}
