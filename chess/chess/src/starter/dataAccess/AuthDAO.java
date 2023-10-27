package dataAccess;
import models.Authtoken;
import models.Game;
import models.User;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * Manages Authentication Tokens
 */
public class AuthDAO {
    /**
     * List of authTokens
     */
    private static Map<String, String> tokenList = new HashMap<>();

    /**
     * default constructor. Initialized the tokenList.
     */

    /**
     * clears the tokenList
     * @throws DataAccessException if list is already empty
     */
    public static void clear(){
        tokenList.clear();
    }

    /**
     * Inserts an authtoken into the list.
     * @param username to be inserted.
     * @throws DataAccessException if the token is already in list.
     */
    public static void insertAuth(String username) throws DataAccessException{
        if(tokenList.containsKey(username)){
            throw new DataAccessException("Token already exists");
        }
        tokenList.put(username, UUID.randomUUID().toString());
    }

    public static String getAuthToken(String username){
        return tokenList.get(username);
    }
    /**
     *returns the list of all auth tokens
     * @return the tokenList
     * @throws DataAccessException if there aren't any tokens to return
     */
    public List<Authtoken> getAllTokens() throws DataAccessException{
//        return new ArrayList<>(tokenList);
        //FIX ME
        return null;
    }



}
