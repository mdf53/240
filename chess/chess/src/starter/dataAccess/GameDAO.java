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
    public static void createGame(Game g, String authToken) throws DataAccessException, SQLException {
        //add a new game to the map
        String whiteUsername = g.getWhiteUsername();
        String blackUsername = g.getBlackUsername();
        String name = g.getGameName();
        String game = g.serialize();

        if(!AuthDAO.invalidToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        if(gameExists(name)){
            throw new DataAccessException("Error: Game Already Exists");
        }
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("INSERT INTO games (id, whiteUsername, blackUsername, gameName, game) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, authToken);
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

    private static boolean gameExists(String gameName) throws DataAccessException {
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
        String gameName;
        String whiteU;
        String blackU;
        String authToken;
        String game;
        ArrayList<Game> result = new ArrayList<>();
        Connection con = Database.getConnection();
        try (var preparedStatement = con.prepareStatement("SELECT * FROM games")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                gameName = resultSet.getString("gameName");
                whiteU = resultSet.getString("whiteUsername");
                blackU = resultSet.getString("blackUsername");
                authToken = resultSet.getString("id");
                game = resultSet.getString("game");
                result.add(new Game(gameName, whiteU, blackU, authToken, game));
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
     * @param id  is the id for game to be joined.
     * @throws DataAccessException if there's already a user of that color in that game
     */
    public static void joinGame(String id, String playerColor, String authToken) throws DataAccessException, SQLException {
        //User joins game
        Integer token = Integer.parseInt(id);
        if(!AuthDAO.invalidToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        Game g = new Game(null);
        g.setGameID(token);
        boolean foundID = false;
        ArrayList<Game> gameList = getAllGames();
        for(Game game: gameList){
            if(Objects.equals(game.getGameID(), g.getGameID())){
                g = game;
                if(Objects.equals(playerColor, "WHITE") && game.getWhiteUsername() != null){
                    throw new DataAccessException("Error: bad request");
                }
                if(Objects.equals(playerColor, "BLACK") && game.getBlackUsername() != null){
                    throw new DataAccessException("Error: bad request");
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
        try (var preparedStatement = con.prepareStatement("UPDATE game SET" + userToUpdate + " = ? WHERE gameID = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, String.valueOf(g.getGameID()));
            preparedStatement.executeUpdate();
            con.close();
        }catch(SQLException e){
            throw new DataAccessException("Error: " + e.getMessage());
        }
//        if(Objects.equals(playerColor, "BLACK") && g.getBlackUsername() != null){
//            throw new DataAccessException("Error: already taken");
//        } else if (Objects.equals(playerColor, "WHITE") && g.getWhiteUsername() != null){
//            throw new DataAccessException("Error: already taken");
//        } else if(Objects.equals(playerColor, "BLACK")){
//            g.setBlackUsername(username);
//        } else if(Objects.equals(playerColor, "WHITE")){
//            g.setWhiteUsername(username);
//        }

    }


//    /**
//     * removes a game from the map
//     * @param game is the game to be removed
//     * @throws DataAccessException when the game doesn't exist
//     */
//    public void removeGame(Game game) throws DataAccessException{
//        //Removes a singular game from map.
//        gameMap.remove(game.getGameName());
//    }

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
