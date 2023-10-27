package services;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import models.User;
import requests.LoginRequest;
import results.LoginResult;
import results.RegisterResults;

/**
 * Service for Login API
 */
public class LoginService {
    /**
     * Logs in user
     * @param request login request
     * @return login results
     */
    public LoginResult login(LoginRequest request) throws DataAccessException{
//
//        if(UserDAO.loginRequest(request.getUsername(), request.getPassword())){
//            return new LoginResult(request.getUsername(), request.getPassword());
//        } else{
//            return new LoginResult("User not found");
//        }
        LoginResult results = new LoginResult(null);
        try {
            User u = new User(request.getUsername(), request.getPassword(), null);
            if(UserDAO.alreadyUser(u)) {

            }
        }catch(DataAccessException ex){
            results.setMessage(ex.getMessage());
        }
        return results;    }
}
