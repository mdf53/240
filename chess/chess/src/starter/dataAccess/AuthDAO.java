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
    private static Set<Authtoken> tokenList;

    /**
     * default constructor. Initialized the tokenList.
     */
    public AuthDAO(){
        tokenList = new HashSet<>();
    }

    /**
     * clears the tokenList
     * @throws DataAccessException if list is already empty
     */
    public static void clear() throws DataAccessException {
        if(tokenList.isEmpty()){
            throw new DataAccessException("Token List is already empty");
        }
        tokenList.clear();
    }

    /**
     * Inserts an authtoken into the list.
     * @param token to be inserted.
     * @throws DataAccessException if the token is already in list.
     */
    public void insertAuth(Authtoken token) throws DataAccessException{
        if(tokenList.contains(token)){
            throw new DataAccessException("Token already exists");
        }
        tokenList.add(token);
    }

    /**
     *returns the list of all auth tokens
     * @return the tokenList
     * @throws DataAccessException if there aren't any tokens to return
     */
    public List<Authtoken> getAllTokens() throws DataAccessException{
        return new ArrayList<>(tokenList);
    }



}
