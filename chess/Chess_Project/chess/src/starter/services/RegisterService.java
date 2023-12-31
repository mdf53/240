package services;
import dataAccess.*;
import models.Authtoken;
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
    public RegisterResults register(RegisterRequest request) {
            RegisterResults results = new RegisterResults(null);
            if(request.getEmail() == null || request.getPassword() == null || request.getUsername() == null){
                results.setMessage("Error: bad request");
            } else {
                try {
                    User u = new User(request.getUsername(), request.getPassword(), request.getEmail());
                    if (!UserDAO.alreadyUser(u)) {
                        Authtoken token = UserDAO.addNewUser(u);
                        results.setUsername(u.getUsername());
                        results.setAuthToken(token.getAuthToken());
                    } else {
                        results.setMessage("Error: already taken");
                    }
                } catch (DataAccessException ex) {
                    results.setMessage(ex.getMessage());
                }
            }
            return results;
    }
}
