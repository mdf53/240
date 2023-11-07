package models;

import java.util.UUID;

import static java.lang.Math.abs;
import chess.ChessGame;
import chess.MyChessGame;

/**
 * Game object.
 */
public class Game {
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

    public Game(String gameName, String whiteU, String blackU, String authToken, String game) {
        this.gameName = gameName;
        whiteUsername = whiteU;
        blackUsername = blackU;
        gameID = Integer.valueOf(authToken);
        //figure out game
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
}
