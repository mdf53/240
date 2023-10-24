package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Game object.
 */
public class Game {
    /**
     * ID for game
     */
    private String gameID;
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
    private List<String> observerList;


    /**
     * Constructor
     * @param iD gameId
     * @param wUser whiteUsername
     * @param bUser blackUsername
     * @param name gameName
     */
    public Game(String iD, String wUser, String bUser, String name){
    gameID = iD;
    whiteUsername = wUser;
    blackUsername = bUser;
    gameName = name;
    observerList = new ArrayList<>();
//    game = g;
}
    public Game(String ID, String name){
        gameID = ID;
        whiteUsername = null;
        blackUsername = null;
        gameName = name;
        observerList = new ArrayList<>();
    }

public String getGameID(){
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
public void addObservor(String name){
        observerList.add(name);
}
public List<String> getObserverList(){
        return observerList;
}
public String getGameName(){
    return gameName;
}



}
