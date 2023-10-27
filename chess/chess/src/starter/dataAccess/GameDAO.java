package dataAccess;
import models.Game;

import java.util.*;

/**
 * Manages the data storage of the games.
 * */
public class GameDAO {
    /**
     * Map for the games. Has the name and the Game
     */
    private static Map<String, Game> gameMap = new HashMap<>();

    /**
     * Default constructor. Initialized the map for storing the games w/ the IDs.
     */

    /**
     * "creates" a game by adding it to the map.
     * @param g is the game being created (added to game map).
     * @throws DataAccessException if the game already exists
     */
    public static void createGame(Game g, String authToken) throws DataAccessException{
        //add a new game to the map
        if(AuthDAO.invalidToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        if(gameMap.containsValue(g)){
            throw new DataAccessException("Game already exists.");
        }
        gameMap.put(g.getGameName(), g);
    }

    /**
     * Returns a list of the games.
     * @return the list of all of the games.
     * @throws DataAccessException if there are no games
     */
    public static ArrayList<Game> getAllGames() throws DataAccessException{
        //return all the games in the map.
        ArrayList<Game> result = new ArrayList<>();
        for(Map.Entry<String, Game> entry: gameMap.entrySet()){
            Game value = entry.getValue();
            result.add(value);
        }
        return result;

    }

    /**
     * allows a player to join a game
     * @param id  is the id for game to be joined.
     * @throws DataAccessException if there's already a user of that color in that game
     */
    public static void joinGame(String id, String playerColor, String authToken) throws DataAccessException{
        //User joins game
        Integer token = Integer.parseInt(id);
        if(AuthDAO.invalidToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        Game g = new Game(null);
        g.setGameID(token);
        boolean foundID = false;
        for(Game game: gameMap.values()){
            if(Objects.equals(game.getGameID(), g.getGameID())){
                g = game;
                foundID = true;
                break;
            }
        }
        if(!foundID){
            throw new DataAccessException("Error: bad request");
        }
        String username = AuthDAO.getUsername(authToken);
        if(playerColor == null){
            observeGame(g, id);
        }
        if(Objects.equals(playerColor, "BLACK") && g.getBlackUsername() != null){
            throw new DataAccessException("Error: already taken");
        } else if (Objects.equals(playerColor, "WHITE") && g.getWhiteUsername() != null){
            throw new DataAccessException("Error: already taken");
        } else if(Objects.equals(playerColor, "BLACK")){
            g.setBlackUsername(username);
        } else if(Objects.equals(playerColor, "WHITE")){
            g.setWhiteUsername(username);
        }
    }

    /**
     * Lets a player observe a game.
     * @param game is the game to be observed
     * @throws DataAccessException if game doesn't exist
     */
    public static void observeGame(Game game, String id) throws DataAccessException{
        //User observes Game
        if(!gameMap.containsKey(game.getGameName())){
            throw new DataAccessException("Error: bad request");
        }
    }

    /**
     * removes a game from the map
     * @param game is the game to be removed
     * @throws DataAccessException when the game doesn't exist
     */
    public void removeGame(Game game) throws DataAccessException{
        //Removes a singular game from map.
        gameMap.remove(game.getGameName());
    }

    /**
     * Clears the gameMap
     */
    public static void clear(){
        //clear all games
        gameMap.clear();
    }

}
