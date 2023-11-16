package results;

import models.Game;

import java.util.ArrayList;
import java.util.List;

public class ListGamesResults {
    /**
     * error message
     */
    String message;

    ArrayList<Game> games = new ArrayList<>();

    public ArrayList<Game> getGameList() {
        return games;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.games = gameList;
    }

    public ListGamesResults(String m){
        message = m;
        games = new ArrayList<>();
    }
    public String getMessage(){
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
