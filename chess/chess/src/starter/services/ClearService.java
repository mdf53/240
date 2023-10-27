package services;
import dataAccess.*;
import results.ClearResult;

/**
 * Service for Clear API
 */
public class ClearService {
    /**
     * Clears all the DAOs
     */
    public ClearResult clear(){
        AuthDAO.clear();
        GameDAO.clear();
        UserDAO.clear();
        return new ClearResult();
    }
}
