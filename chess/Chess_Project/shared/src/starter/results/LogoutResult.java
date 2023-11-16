package results;

/**
 * Results for Logout API
 */
public class LogoutResult {
    //if not able to log out return error 401 or 500.
    //401: "Error: unauthroized"
    //505: "Error: description"
    /**
     * error message
     */
    String message;

    /**
     * Successful Constructor. No message necessary
     */
    public LogoutResult(){
        message = null;
    }

    /**
     * Failed Constructor
     * @param m Error message
     */
    public LogoutResult(String m){
        message = m;
    }
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
