package dataAccess;
import models.Authtoken;
import dataAccess.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static dataAccess.Database.getConnection;
import static java.sql.DriverManager.getConnection;

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
    public static void clear() throws DataAccessException {

        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("DELETE FROM authTokens ;")) {

            preparedStatement.executeUpdate();
            Database.closeConnection(con);
        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }

    }

    /**
     * Inserts an authtoken into the list.
     * @param token to be inserted.
     * @throws DataAccessException if the token is already in list.
     */
    public static void insertAuth(Authtoken token) throws DataAccessException{

        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("INSERT INTO authTokens (token, username) VALUES(?, ?)")) {
            preparedStatement.setString(1, token.getAuthToken());
            preparedStatement.setString(2, token.getUsername());

            preparedStatement.executeUpdate();
            Database.closeConnection(con);

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
//        for(Map.Entry<String, Authtoken> authtokenMap: tokenList.entrySet()){
//            if(Objects.equals(authtokenMap.getKey(), authToken)){
//                tokenList.remove(authtokenMap.getKey());
//                return;
//            }
//        }
        Connection con = getConnection();
        try(PreparedStatement prep = con.prepareStatement("DELETE FROM authTokens WHERE token = ?")){
            prep.setString(1, authToken);
            prep.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new DataAccessException("Error: " + e.getMessage());
        }


//        throw new DataAccessException("Error: unauthorized");
    }

    public static boolean invalidToken(String authToken) throws DataAccessException, SQLException {
        Connection conn = Database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM authTokens WHERE token=?")) {
            preparedStatement.setString(1, authToken);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("token");
                    if(id.equals(authToken)){
                        return true;
                    }
                }
            }
        } catch(SQLException e){
            throw new DataAccessException("Error: " + e.getMessage());
        }
        return false;
//        return !tokenList.containsKey(authToken);
    }



}
