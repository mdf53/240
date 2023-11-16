package models;

/**
 * Authtoken object. Has string for the token and name.
 */
public class Authtoken {
    /**
     * Token name
     */
    private String authToken;
    /**
     * username
     */
    private String username;

    /**
     * Constructor that sets variables
     * @param token that will be set to authToken
     * @param name that will be set to username
     */
    public Authtoken(String name, String token){
        authToken = token;
        username = name;
    }


    public String getUsername(){
        return username;
    }
    public String getAuthToken(){
        return authToken;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
