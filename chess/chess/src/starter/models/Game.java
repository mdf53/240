package models;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Math.abs;
import chess.*;

/**
 * Game object.
 */
public class Game implements Comparable<Game> {
    /**
     * ID for game
     */
    private Integer gameID;
    /**
     * username for white player
     */
    private String whiteUsername;
    /**
     * username for black player
     */
    private String blackUsername;
    /**
     * name of game
     */
    private String gameName;

    private ChessGame game;

    public Game(String gameName, String whiteU, String blackU, int authToken, String game) {
        this.gameName = gameName;
        whiteUsername = whiteU;
        blackUsername = blackU;
        gameID = Integer.valueOf(authToken);
        //figure out game
    }

    public Game(String gameName, String whiteU, String blackU, int gameID) {
        this.gameName = gameName;
        whiteUsername = whiteU;
        blackUsername = blackU;
        this.gameID = gameID;
    }


    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }


    public Game(String name){
        gameID = abs(UUID.randomUUID().hashCode());
        whiteUsername = null;
        blackUsername = null;
        gameName = name;
        game = new MyChessGame("set");

    }

public Integer getGameID(){
    return gameID;
}
public void setWhiteUsername(String username){
        whiteUsername = username;
}
public String getWhiteUsername(){
    return whiteUsername;
}
public void setBlackUsername(String username){
        blackUsername = username;
}
public String getBlackUsername(){
    return blackUsername;
}
public String getGameName(){
    return gameName;
}


    public String serialize() {
        return game.serialize();
    }

    public void setGameName(String id) {
        gameName = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameID, game.gameID) && Objects.equals(whiteUsername, game.whiteUsername) && Objects.equals(blackUsername, game.blackUsername) && Objects.equals(gameName, game.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, gameName);
    }

    @Override
    public int compareTo(Game o) {
        return o.gameID - this.gameID;
    }
}
