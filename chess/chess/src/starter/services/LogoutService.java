package services;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import results.LogoutResult;

import java.sql.SQLException;

/**
 * Service for Logout API
 */
public class LogoutService {

    /**
     * Logs out User
     * @param authToken user to logout
     * @return Results
     */
    public LogoutResult logout(String authToken){
        LogoutResult result = new LogoutResult();
        try {
            AuthDAO.removeToken(authToken);
        } catch(DataAccessException | SQLException ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }
}
