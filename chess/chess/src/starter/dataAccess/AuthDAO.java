package dataAccess;
import models.Authtoken;
import dataAccess.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static dataAccess.Database.getConnection;

/**
 * Manages Authentication Tokens
 */
public class AuthDAO {
    /**
     * Map of auth tokens. Authtoken string, Authtoken that contains username
     */
    private static Map<String, Authtoken> tokenList = new HashMap<>();

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
     * @param token to be inserted.
     * @throws DataAccessException if the token is already in list.
     */
    public static void insertAuth(Authtoken token) throws DataAccessException{
//        if(tokenList.containsKey(token.getAuthToken())){
//            throw new DataAccessException("Error");
//        }
//        tokenList.put(token.getAuthToken(), token);
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("INSERT INTO authTokens (token, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, token.getAuthToken());
            preparedStatement.setString(2, token.getUsername());

            preparedStatement.executeUpdate();
            con.close();

        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }


    }

    public static String getUsername(String authToken){
        for(String tokenString: tokenList.keySet()){
            if(Objects.equals(tokenString, authToken)){
                return tokenList.get(authToken).getUsername();
            }
        }
        return "";
    }
    /**
     *returns the list of all auth tokens
     * @return the tokenList
     * @throws DataAccessException if there aren't any tokens to return
     */

    public static void removeToken(String authToken) throws DataAccessException{
        for(Map.Entry<String, Authtoken> authtokenMap: tokenList.entrySet()){
            if(Objects.equals(authtokenMap.getKey(), authToken)){
                tokenList.remove(authtokenMap.getKey());
                return;
            }
        }
        throw new DataAccessException("Error: unauthorized");
    }

    public static boolean invalidToken(String authToken){
        return !tokenList.containsKey(authToken);
    }



}
