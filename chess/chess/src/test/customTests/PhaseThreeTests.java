package customTests;
import dataAccess.DataAccessException;
import models.*;
import org.junit.jupiter.api.*;
import requests.*;
import results.*;
import services.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PhaseThreeTests {

    @BeforeEach
    public void setup() throws DataAccessException {
        ClearService service = new ClearService();
        service.clear();
    }
    //Member functions for login user, create user and create game. Make sure they return a response.
    //Test register
    private LoginResult loginUser(){
        LoginRequest req1 = new LoginRequest("John", "Pass");
        LoginService ser1 = new LoginService();
        return ser1.login(req1);    }
    private CreateGameResult createGame(String authToken){
        CreateGameService service = new CreateGameService();
        CreateGameRequest request = new CreateGameRequest("Demolition Dojo");
        return service.createGame(request, authToken);
    }
    private RegisterResults registerNewUser(){
        RegisterRequest request = new RegisterRequest("John", "Pass", "gmail");
        RegisterService service = new RegisterService();
        return service.register(request);
    }
    private void joinGame(User user, Game game){

    }

    @Test
    @Order(1)
    @DisplayName("Register A User")
    public void successRegister(){

        RegisterResults result = registerNewUser();

        //first is expecting, second is passing, third is what it returns if it fails
        assertNull(result.getMessage(), result.getMessage());
        assertEquals(result.getUsername(), "John", "Username does not equal");
        assertNotNull(result.getAuthToken(), "AuthToken is null");
    }
    @Test
    @Order(2)
    @DisplayName("Repeat A User Registration")
    public void failRegister(){
        //register a user
        RegisterResults res = registerNewUser();


        //reregister the user

        RegisterResults result = registerNewUser();

        assertEquals(result.getMessage(), "Error: already taken");
    }

    //Test login
    @Test
    @Order(3)
    @DisplayName("Successful Login")
    public void successLogin(){
        RegisterResults res = registerNewUser();



        LoginResult result = loginUser();

        //first is expecting, second is passing, third is what it returns if it fails
        assertNull(result.getMessage(), result.getMessage());
        assertEquals(result.getUsername(), "John", "Error: bad request");
        assertNotNull(result.getAuthToken(), "Error: unauthorized");
    }
    @Test
    @Order(4)
    @DisplayName("Failed Login")
    public void failLogin(){

        LoginRequest req = new LoginRequest("Bilbo", "Pass");
        LoginService ser = new LoginService();
        LoginResult result = ser.login(req);

        //first is expecting, second is passing, third is what it returns if it fails
        assertEquals("Error: unauthorized", result.getMessage());
    }

    //Test logout
    @Test
    @Order(4)
    @DisplayName("Successful Logout")
    public void successLogout(){
        RegisterResults result = registerNewUser();

        LoginResult res1 = loginUser();

        LogoutService service = new LogoutService();
        LogoutResult resu = service.logout(result.getAuthToken());

        //first is expecting, second is passing, third is what it returns if it fails
        assertNull(resu.getMessage());
    }
    @Test
    @Order(5)
    @DisplayName("Failed Logout")
    public void failedLogout(){
        RegisterResults result = registerNewUser();

        LoginRequest req1 = new LoginRequest("Jim", "Pass");
        LoginService ser1 = new LoginService();
        LoginResult res1 = ser1.login(req1);

        LogoutService service = new LogoutService();
        LogoutResult resu = service.logout(res1.getAuthToken());

        //first is expecting, second is passing, third is what it returns if it fails
        assertEquals("Error: unauthorized", resu.getMessage());
    }
    //Test create game
    @Test
    @Order(6)
    @DisplayName("Successful Create Game")
    public void successCreateGame(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();

        CreateGameService service = new CreateGameService();
        CreateGameRequest request = new CreateGameRequest("Demolition Dojo");
        CreateGameResult result = service.createGame(request, registerResults.getAuthToken());
        //first is expecting, second is passing, third is what it returns if it fails
        assertNull(result.getMessage());
    }
    @Test
    @Order(7)
    @DisplayName("Failed Create Game")
    public void failCreateGame(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();

        CreateGameService ser = new CreateGameService();
        CreateGameRequest req = new CreateGameRequest("Demolition Dojo");
        CreateGameResult res = ser.createGame(req, registerResults.getAuthToken());

        CreateGameService service = new CreateGameService();
        CreateGameRequest request = new CreateGameRequest("Demolition Dojo");
        CreateGameResult result = service.createGame(request, loginResult.getAuthToken());


        //first is expecting, second is passing, third is what it returns if it fails
        assertEquals("Error: unauthorized",result.getMessage());
    }
    //Test join game
    @Test
    @Order(11)
    @DisplayName("Successful List Game")
    public void successListGame(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();
        CreateGameResult createGameResult = createGame(registerResults.getAuthToken());

        ListGamesService listGamesService = new ListGamesService();
        ListGamesResults listGamesResults = listGamesService.listGames(registerResults.getAuthToken());

        assertNull(listGamesResults.getMessage());
    }
    @Test
    @Order(10)
    @DisplayName("Failed Join Game")
    public void failJoinGame(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();
        CreateGameResult createGameResult = createGame(registerResults.getAuthToken());

        JoinGameService service = new JoinGameService();
        JoinGameRequest request = new JoinGameRequest("BLACK", createGameResult.getGameID().toString());
        JoinGameResult result = service.joinGame(request, loginResult.getAuthToken());

        assertEquals("Error: unauthorized", result.getMessage());
    }
    //Test list games
    @Test
    @Order(9)
    @DisplayName("Successful Join Game")
    public void successJoinGames(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();
        CreateGameResult createGameResult = createGame(registerResults.getAuthToken());

        JoinGameService service = new JoinGameService();
        JoinGameRequest request = new JoinGameRequest("BLACK", createGameResult.getGameID().toString());
        JoinGameResult result = service.joinGame(request, loginResult.getAuthToken());

        assertEquals("Error: unauthorized", result.getMessage());
    }
    @Test
    @Order(12)
    @DisplayName("Failed List Games")
    public void failedListGames(){
        RegisterResults registerResults = registerNewUser();
        LoginResult loginResult = loginUser();
        CreateGameResult createGameResult = createGame(registerResults.getAuthToken());

        JoinGameService service = new JoinGameService();
        JoinGameRequest request = new JoinGameRequest("BLACK", createGameResult.getGameID().toString());
        JoinGameResult result = service.joinGame(request, loginResult.getAuthToken());

        assertEquals("Error: unauthorized", result.getMessage());
    }

    //Test clear
    @Test
    @Order(8)
    @DisplayName("Successful Clear")
    public void clear() throws DataAccessException {
        ClearService service = new ClearService();
        ClearResult result = service.clear();
        assertNull(result.getMessage());
    }

}
