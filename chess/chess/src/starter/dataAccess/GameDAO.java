package dataAccess;
import models.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Manages the data storage of the games.
 * */
public class GameDAO {

    /**
     * Default constructor. Initialized the map for storing the games w/ the IDs.
     */

    /**
     * "creates" a game by adding it to the map.
     * @param g is the game being created (added to game map).
     * @throws DataAccessException if the game already exists
     */
    public static void createGame(Game g, String userAuthToken) throws DataAccessException, SQLException {
        //add a new game to the map
        int gameID = g.getGameID();
        String whiteUsername = g.getWhiteUsername();
        String blackUsername = g.getBlackUsername();
        String name = g.getGameName();
        String game = g.serialize();

        if(!AuthDAO.invalidToken(userAuthToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        if(gameExists(name)){
            throw new DataAccessException("Error: Game Already Exists");
        }
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("INSERT INTO games (id, whiteUsername, blackUsername, gameName, game) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, gameID);
            preparedStatement.setString(2, whiteUsername);
            preparedStatement.setString(3, blackUsername);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, game);

            preparedStatement.executeUpdate();
            Database.closeConnection(con);

        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

    public static boolean gameExists(String gameName) throws DataAccessException {
        Connection conn = Database.getConnection();
        try (var preparedStatement = conn.prepareStatement("SELECT * FROM games WHERE gameName=?")) {
            preparedStatement.setString(1, gameName);
            try (var rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("gameName");
                    if(id.equals(gameName)){
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
     * Returns a list of the games.
     * @return the list of all of the games.
     * @throws DataAccessException if there are no games
     */
    public static ArrayList<Game> getAllGames() throws DataAccessException{
        //return all the games in the map.

        ArrayList<Game> result = new ArrayList<>();
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("SELECT * FROM games")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String gameName = resultSet.getString("gameName");
                String whiteU = resultSet.getString("whiteUsername");
                String blackU = resultSet.getString("blackUsername");
                int gameID = resultSet.getInt("id");
                String game = resultSet.getString("game");
                result.add(new Game(gameName, whiteU, blackU, gameID, game));
            }
            Database.closeConnection(con);
        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }
        return result;

    }

    /**
     * allows a player to join a game
     * @param name  is the name for game to be joined.
     * @throws DataAccessException if there's already a user of that color in that game
     */
    public static void joinGame(String name, String playerColor, String authToken) throws DataAccessException, SQLException {

        if(playerColor == null) return;

        //User joins game

        Game g = new Game(null);
        g.setGameName(name);
        boolean foundID = false;
        ArrayList<Game> gameList = getAllGames();
        for(Game game: gameList){
            if(Objects.equals(game.getGameName(), g.getGameName())){
                g = game;
                if(Objects.equals(playerColor, "WHITE") && g.getWhiteUsername() != null){
                    throw new DataAccessException("Error: already taken");
                }
                if(Objects.equals(playerColor, "BLACK") && g.getBlackUsername() != null){
                    throw new DataAccessException("Error: already taken");
                }
                foundID = true;
                break;
            }
        }
        if(!foundID){
            throw new DataAccessException("Error: bad request");
        }

        String username = AuthDAO.getUsername(authToken);
        String userToUpdate = (Objects.equals(playerColor, "WHITE")) ? "whiteUsername" : "blackUsername";
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("UPDATE games SET " + userToUpdate + " = ? WHERE id = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, g.getGameID());
            preparedStatement.executeUpdate();
            con.close();
        }
//        catch(SQLException e){
//            throw new DataAccessException("Error: " + e.getMessage());
//        }

    }


    /**
     * Clears the gameMap
     */
    public static void clear() throws DataAccessException {
        //clear all games
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("DELETE FROM games ;")) {

            preparedStatement.executeUpdate();
            Database.closeConnection(con);
        }
        catch(SQLException ex){
            throw new DataAccessException("Error: " + ex.getMessage());
        }
    }

}
