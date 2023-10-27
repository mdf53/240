package services;
import dataAccess.DataAccessException;
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

        if(request.registerUser()){
            return new RegisterResults(request.getUsername(), request.getPassword(), request.getEmail());
        }
        return new RegisterResults("Unable to register User.");
    }
}
