package dataAccess;
import models.Authtoken;
import models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

/***
 * Stores and manages User data
 */
public class UserDAO {
//    /**
//     * Map of Users
//     */
//    private static Map<String, User> userMap = new HashMap<>();


    /**
     * clears map.
     * @throws DataAccessException if map is empty
     */
    public static void clear() throws DataAccessException {
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("DELETE FROM users ;")) {

            preparedStatement.executeUpdate();
            Database.closeConnection(con);
        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }    }

    /**
     * checks to see if user is in map
     * @param user that is being checked
     * @return true if already in, false if not
     * @throws DataAccessException if user is null
     */
    public static boolean alreadyUser(User user) throws DataAccessException{
//        if(userMap.containsKey(user.getUsername())){
//            throw new DataAccessException("Error: already taken");
//        }
//        return userMap.containsValue(user);
        Connection conn = Database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
            preparedStatement.setString(1, user.getUsername());
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    if(username.equals(user.getUsername())){
                        return true;
                    }
                }
            }
        } catch(SQLException e){
            throw new DataAccessException("Error: " + e.getMessage());
        }
        return false;
    }


    /**
     * adds new user to map
     * @param u user to be added
     * @throws DataAccessException if user is null or already in the map
     */
    public static Authtoken addNewUser(User u) throws DataAccessException{
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("INSERT INTO users (username, password, email) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getEmail());
            Authtoken token = new Authtoken(u.getUsername(), UUID.randomUUID().toString());
            AuthDAO.insertAuth(token);

            preparedStatement.executeUpdate();
            Database.closeConnection(con);
            return token;
        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    /**
     * Finds a user off of a username
     * @param name is the username to be checked
     * @return the user
     * @throws DataAccessException if username isn't connected to a user
     */
    public static User findUser(String name) throws DataAccessException{
//        if(userMap.containsKey(name)){
//            return userMap.get(name);
//        }
//        throw new DataAccessException("Error: unauthorized");
        String username = null;
        String password = null;
        String email = null;
        Connection conn = Database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
            preparedStatement.setString(1, name);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    username = rs.getString("username");
                    if(Objects.equals(username, name)){
                        password = rs.getString("password");
                        email = rs.getString("email");
                        return new User(username, password, email);
                    }
                }
            }
        } catch(SQLException e){
            throw new DataAccessException("Error: " + e.getMessage());
        }
        return new User("","","");
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
