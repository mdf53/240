package results;

/**
 * Results for Login API
 */
public class LoginResult {
    /**
     * error message
     */
    private String message;
    /**
     * authentication token
     */
    private String authToken;
    /**
     * Username
     */
    private String username;

    /**
     * Successful Constructor
     * @param token is the authToken string
     * @param name is the username string
     */
    public LoginResult(String token, String name){
        message =  null;
        authToken = token;
        username = name;
    }

    /**
     * Failed Constructor
     * @param m error message
     */
    public LoginResult(String m){
        message = m;
        authToken = null;
        username = null;
    }
    // … Getters and Setters for message, authToken, and username properties

    public String getUsername(){
        return username;
    }
    public String getMessage(){
        return message;
    }
    public String getAuthToken(){
        return authToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
