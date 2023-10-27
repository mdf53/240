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
    private String password;
    /**
     * new email
     */
    private String email;
    /**
     * error message
     */
    private String message;

    /**
     * Successful Constructor
     * @param name is the username
     * @param pass is the password
     * @param mail is the email
     */
    public RegisterResults(String name, String pass, String mail){
        username = name;
        password = pass;
        email = mail;
        message = null;
    }


    /**
     * Failed Constructor
     * @param m is the error message
     */
    public RegisterResults(String m){
        username = null;
        password = null;
        email = null;
        message = m;
    }
    public String getMessage(){
        return message;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

}
