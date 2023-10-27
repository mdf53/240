package dataAccess;
import models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public static boolean loginRequest(String username, String password){
        if(userMap.containsKey(username)){
            User u = userMap.get(username);
            return Objects.equals(u.getPassword(), password);
        }
        return false;
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
    public static void addNewUser(User u) throws DataAccessException{
        if(u.getPassword()==null) {
            throw new DataAccessException("Error: bad request");
        }
        if(!alreadyUser(u)){
            userMap.put(u.getUsername(), u);
            AuthDAO.insertAuth(u.getUsername());
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
        throw new DataAccessException("Error: bad request");
    }

}
