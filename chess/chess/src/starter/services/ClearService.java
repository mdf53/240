package services;
import dataAccess.*;

/**
 * Service for Clear API
 */
public class ClearService {
    /**
     * Clears all the DAOs
     */
    public void clear() throws DataAccessException {
        try{
            AuthDAO.clear();
            GameDAO.clear();
            UserDAO.clear();
        }catch (DataAccessException ex){

        }

    }
}
