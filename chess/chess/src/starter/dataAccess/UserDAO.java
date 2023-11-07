package dataAccess;
import models.Authtoken;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/***
 * Stores and manages User data
 */
public class UserDAO {
    /**
     * Map of Users
     */
    private static Map<String, User> userMap = new HashMap<>();

    /**
     * Default constructor. Initializes userMap
     */


    /**
     * clears map.
     * @throws DataAccessException if map is empty
     */
    public static void clear() {
        userMap.clear();
    }

    /**
     * checks to see if user is in map
     * @param user that is being checked
     * @return true if already in, false if not
     * @throws DataAccessException if user is null
     */
    public static boolean alreadyUser(User user) throws DataAccessException{
        if(userMap.containsKey(user.getUsername())){
            throw new DataAccessException("Error: already taken");
        }
        return userMap.containsValue(user);
    }


    /**
     * adds new user to map
     * @param u user to be added
     * @throws DataAccessException if user is null or already in the map
     */
    public static Authtoken addNewUser(User u) throws DataAccessException{
        if(u.getPassword()==null) {
            throw new DataAccessException("Error: bad request");
        }
        if(!alreadyUser(u)){
            userMap.put(u.getUsername(), u);
            Authtoken token = new Authtoken(u.getUsername(), UUID.randomUUID().toString());
            AuthDAO.insertAuth(token);
            return token;
        } else{
            throw new DataAccessException("Error: bad request");
        }
    }

    /**
     * Finds a user off of a username
     * @param name is the username to be checked
     * @return the user
     * @throws DataAccessException if username isn't connected to a user
     */
    public static User findUser(String name) throws DataAccessException{
        if(userMap.containsKey(name)){
            return userMap.get(name);
        }
        throw new DataAccessException("Error: unauthorized");
    }

    public static boolean loginAttempt(User a, User b) throws DataAccessException{
        if(!Objects.equals(a.getUsername(), b.getUsername())){
            throw new DataAccessException("Error: unauthorized");
        }
        if(!Objects.equals(a.getPassword(), b.getPassword())){
            throw new DataAccessException("Error: unauthorized");
        }
        return true;

    }

}
