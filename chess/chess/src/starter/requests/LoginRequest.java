package requests;

import dataAccess.UserDAO;
import results.LoginResult;
import results.LogoutResult;

/**
 * Request for the Login API
 */
public class LoginRequest {
    /**
     * name of user
     */
    private String username;
    /**
     * user password
     */
    private String password;

    /**
     * Constructor that initialized the variables
     * @param user is the username
     * @param pass is the password
     */
    public LoginRequest(String user, String pass){
        username = user;
        password = pass;
    }
    public LoginResult login(){
        if(UserDAO.loginRequest(username, password)){
            return new LoginResult(username, password);
        } else{
            return new LoginResult("User not found");
        }
    }

        // … Getters and Setters for username and password properties
    public String getUsername(){
        return username;
    }
     public String getPassword(){
        return password;
     }


}
