package results;

public class ListGamesResults {
    /**
     * error message
     */
    String message;
    /**
     * ID of game
     */
    String gameID;
    /**
     * white username
     */
    String whiteUser;
    /**
     * black username
     */
    String blackUser;
    /**
     * name of game
     */
    String gameName;

    public ListGamesResults(String m){
        message = m;
        gameID = null;
        whiteUser = null;
        blackUser = null;
        gameName = null;
    }
    public ListGamesResults(String id, String wu, String bu, String name){
        message = null;
        gameID = id;
        whiteUser = wu;
        blackUser = bu;
        gameName = name;
    }
    public String getMessage(){
        return message;
    }
    public String getGameID(){
        return gameID;
    }
    public String getGameName(){
        return gameName;
    }
    public String getWhiteUser(){
        return whiteUser;
    }
    public String getBlackUser(){
        return blackUser;
    }
}
