package results;

/**
 * Results for Register API
 */
public class RegisterResults {
    /**
     * New username
     */
    private String username;
    /**
     * new password
     */
    private String authToken;
    private String message;

    /**
     * Successful Constructor
     * @param name is the username
     * @param authToken is the authToken
     */
    public RegisterResults(String name, String authToken){
        username = name;
        this.authToken = authToken;
        message = null;
    }


    /**
     * Failed Constructor
     * @param m is the error message
     */
    public RegisterResults(String m){
        username = null;
        authToken = null;
        message = m;
    }
    public String getMessage(){
        return message;
    }
    public String getUsername(){
        return username;
    }
    public String getAuthToken(){
        return authToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
