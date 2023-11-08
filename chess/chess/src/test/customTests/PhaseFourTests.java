package customTests;

import chess.ChessGame;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import models.*;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhaseFourTests {

    @BeforeEach
    public void setup() {
        try{
        GameDAO.clear();
        AuthDAO.clear();
        UserDAO.clear();
        } catch(DataAccessException ex){
            Assertions.assertNull(ex.getMessage());
        }
    }

    @Test
    @DisplayName("Successfully Add AuthToken to Database")
    public void AddToAuthDataSuccess(){
        Authtoken t = new Authtoken("Jimbo", "4");
        try{
            AuthDAO.insertAuth(t);
        }catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Fail To Add AuthToken to Database")
    public void AddToAuthDataFail(){
        Authtoken t = new Authtoken(null, "69");
        try{
            AuthDAO.insertAuth(t);
        }catch(DataAccessException e){
            Assertions.assertNotNull(e.getMessage());
        }
    }

    @Test
    @Order(1)
    @DisplayName("Clear the AuthToken Database")
    public void ClearAuthData(){
        try{
            AuthDAO.clear();
        }catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Successfully Remove a Token From the AuthToken Database")
    public void RemoveAuthData(){
        Authtoken t = new Authtoken("Jimbo", "70");

        try{
            AuthDAO.insertAuth(t);
            AuthDAO.removeToken("70");
        } catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Fail To Remove a Token From the AuthToken Database")
    public void RemoveAuthDataFail(){
        Authtoken t = new Authtoken("Jimbo", "69");

        try{
            AuthDAO.insertAuth(t);
            AuthDAO.removeToken("9999");
        } catch(DataAccessException e){
            Assertions.assertNotNull(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Successfully Validate Token")
    public void ValidateToken() {
        Authtoken t = new Authtoken("Jimbo", "30");
        try {
            AuthDAO.insertAuth(t);
            boolean check = AuthDAO.invalidToken("30");
            Assertions.assertTrue(check);

        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Fail To Validate Token")
    public void ValidateTokenFail(){
        try{
            boolean check = AuthDAO.invalidToken("30000000");
            Assertions.assertFalse(check);

        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("Successfully Add User to Database")
    public void AddUserSuccess(){
        User u = new User("Joe", "mamaPassword", "joemama@gmail.com");
        try{
            UserDAO.addNewUser(u);
        } catch (DataAccessException e) {
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Fail To Add User to Database")
    public void AddUserFail(){

    }

    @Test
    @Order(2)
    @DisplayName("Successfully Clear User Database")
    public void ClearUser(){
        try{
            UserDAO.clear();
        }catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
        }    }

    @Test
    @DisplayName("Successfully Check If User Is In Database")
    public void CheckUser(){
        User u = new User("Joseph", "motherPassword", "serious@email.com");
        try{
            UserDAO.addNewUser(u);
            boolean check = UserDAO.alreadyUser(u);
            Assertions.assertTrue(check);
        } catch (DataAccessException e) {
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Fail Checking If User Is In Database")
    public void CheckUserFail(){
        User u = new User("Joe", "motherPassword", "serious@email.com");
        try{
            boolean check = UserDAO.alreadyUser(u);
            Assertions.assertFalse(check);
        } catch (DataAccessException e) {
            Assertions.assertNull(e.getMessage());
        }
    }

//    @Test
//    @DisplayName("Successfully Login User")
//    public void LoginUser(){
//
//    }
//
//    @Test
//    @DisplayName("Fail To Login User")
//    public void LoginUserFail(){
//
//    }

    @Test
    @DisplayName("Successfully Add Game to Database")
    public void AddGameSuccess(){
        try {
            User u = new User("jo","pass","mail");
            Authtoken t = UserDAO.addNewUser(u);
            Game g = new Game("ILostTheGame");
            GameDAO.createGame(g, t.getAuthToken());
        } catch (SQLException | DataAccessException e) {
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Fail To Add Game to Database")
    public void AddGameFail(){
        try {
            User u = new User("jo","pass","mail");
            Authtoken t = UserDAO.addNewUser(u);
            Game g = new Game("ILostTheGame");
            GameDAO.createGame(g, t.getAuthToken());
            GameDAO.createGame(g, t.getAuthToken());
        } catch (SQLException | DataAccessException e) {
            Assertions.assertEquals("Error: Game Already Exists",e.getMessage());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Successfully Clear Game Database")
    public void ClearGames(){
        try{
            GameDAO.clear();
        }catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Successfully List All Games")
    public void ListGame(){
        try {
            User u = new User("jo","pass","mail");
            Authtoken t = UserDAO.addNewUser(u);
            Game g = new Game("ILostTheGame");
            Game a = new Game("Howdy");
            GameDAO.createGame(g, t.getAuthToken());
            GameDAO.createGame(a, t.getAuthToken());
            ArrayList<Game> games = GameDAO.getAllGames();
            int size = games.size();
            Assertions.assertEquals(2, size);
        } catch (SQLException | DataAccessException e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Fail To List All Games")
    public void ListGameFail(){
        try {
            Game g = new Game("ILostTheGame");
            Game a = new Game("Howdy");
            GameDAO.createGame(g, "12395");
            GameDAO.createGame(a, "12395");
            ArrayList<Game> games = GameDAO.getAllGames();
            int size = games.size();
        } catch (SQLException | DataAccessException e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    @DisplayName("Successfully Join A Games")
    public void JoinGame(){
        try {
            User u = new User("jo","pass","mail");
            Authtoken t = UserDAO.addNewUser(u);
            Game g = new Game("Howdy");
            GameDAO.createGame(g, t.getAuthToken());
            GameDAO.joinGame(g.getGameID(), ChessGame.TeamColor.WHITE, t.getAuthToken());
        } catch (SQLException | DataAccessException e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    @DisplayName("Fail To Join A Games")
    public void JoinGameFail(){
        try {
            Game g = new Game("Hola");
            GameDAO.createGame(g, "125");
            User u = new User("name", "pass", "mail");
            Authtoken token = UserDAO.addNewUser(u);
            GameDAO.joinGame(125, ChessGame.TeamColor.WHITE, token.getAuthToken());
        } catch (SQLException | DataAccessException e) {
            Assertions.assertNotNull(e);
        }
    }
}
