package models;

/**
 * User objects. Holds username, password and email.
 */
public class User {
    /**
     * name of user
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * user email
     */
    private String email;
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername(){
        return username;
    }
}
