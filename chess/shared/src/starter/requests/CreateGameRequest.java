package requests;

/**
 * Request for Create Game API
 */
public class CreateGameRequest {
    /**
     * name of game
     */
    private String gameName;

    /**
     * Constructor that initialzies name
     * @param name is gameName
     */
    public CreateGameRequest(String name) {
        gameName = name;
    }

    public String getGameName(){
        return gameName;
    }

}
