package services;
import dataAccess.*;
import models.User;
import requests.RegisterRequest;
import results.RegisterResults;

/**
 * Service for Register API
 */
public class RegisterService {
    /**
     * Registers User
     * @param request for registration
     * @return results of registration
     */
    public RegisterResults register(RegisterRequest request) throws DataAccessException {
//        UserDAO dao = new UserDAO();
            RegisterResults results = new RegisterResults(null);
            try {
                User u = new User(request.getUsername(), request.getPassword(), request.getEmail());
                if(!UserDAO.alreadyUser(u)) {
                    UserDAO.addNewUser(u);
                    results.setUsername(u.getUsername());
                    results.setAuthToken(AuthDAO.getAuthToken(u.getUsername()));
                }
            }catch(DataAccessException ex){
                results.setMessage(ex.getMessage());
            }
            return results;
    }
}
