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
    private static Map<String, User> userMap;

    /**
     * Default constructor. Initializes userMap
     */
    UserDAO(){
        userMap = new HashMap<>();
    }

    /**
     * clears map.
     * @throws DataAccessException if map is empty
     */
    public static void clear() throws DataAccessException{
        if(userMap.size() == 0){
            throw new DataAccessException("userMap is already empty");
        }
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
        if(user == null){
            throw new DataAccessException("Please enter a valid user");
        }
        return userMap.containsValue(user);
    }


    /**
     * adds new user to map
     * @param u user to be added
     * @throws DataAccessException if user is null or already in the map
     */
    public static void addNewUser(User u) throws DataAccessException{
        if(!alreadyUser(u)){
            //userMap.put(String???, u); insert user with string and User.
            userMap.put(u.getUsername(), u);
        } else{
            throw new DataAccessException("User already exists.");
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
        throw new DataAccessException("Username not connected to a user");
    }

}
