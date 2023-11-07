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

    /**
     *returns the list of all auth tokens
     * @return the tokenList
     * @throws DataAccessException if there aren't any tokens to return
     */

    public static void removeToken(String authToken) throws DataAccessException, SQLException {
        if(invalidToken(authToken)) {
            Connection con = getConnection();
            try (PreparedStatement prep = con.prepareStatement("DELETE FROM authTokens WHERE token = ?")) {
                prep.setString(1, authToken);
                prep.executeUpdate();
                con.close();
            } catch (SQLException e) {
                throw new DataAccessException("Error: " + e.getMessage());
            }
        }
        else{
            throw new DataAccessException("Error: unauthorized");
        }
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
//Implement a find authToken off of username function?

    public static String getToken(String name) throws DataAccessException {
        String token = null;
        Connection conn = Database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM authTokens WHERE username=?")) {
            preparedStatement.setString(1, name);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    if(username.equals(name)){
                        token = rs.getString("token");
                    }
                }
            }
        } catch(SQLException e){
            throw new DataAccessException("Error: " + e.getMessage());
        }
        return token;
    }

    public static String getUsername(String authToken) {
        return null;
    }
}
