package requests;


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

        // … Getters and Setters for username and password properties
    public String getUsername(){
        return username;
    }
     public String getPassword(){
        return password;
     }


}
