package requests;
import models.User;
import dataAccess.UserDAO;

/**
 * Request for Register API
 */
public class RegisterRequest {
    //register new User
    /**
     * new username
     */
    String username;
    /**
     * new password
     */
    String password;
    /**
     * new email
     */
    String email;

    /**
     * Constructor that populates variables
     * @param user is username
     * @param pass is password
     * @param email1 is email
     */
    public RegisterRequest(String user, String pass, String email1){
        username = user;
        password = pass;
        email = email1;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return username;
    }
}
