package models;

import java.util.UUID;

import static java.lang.Math.abs;

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


    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }


    /**
     * Constructor
     * @param wUser whiteUsername
     * @param bUser blackUsername
     * @param name gameName
     */
    public Game(String wUser, String bUser, String name){
    gameID = abs(UUID.randomUUID().hashCode());
    whiteUsername = wUser;
    blackUsername = bUser;
    gameName = name;
}
    public Game(String name){
        gameID = abs(UUID.randomUUID().hashCode());
        whiteUsername = null;
        blackUsername = null;
        gameName = name;
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



}
