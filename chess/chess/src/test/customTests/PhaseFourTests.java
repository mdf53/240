package customTests;

import models.*;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;
import services.ClearService;

import java.sql.SQLException;

public class PhaseFourTests {

    @Test
    @DisplayName("Successfully Add AuthToken to Database")
    public void AddToAuthDataSuccess(){
        Authtoken t = new Authtoken("Jimbo", "69");
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
        Authtoken t = new Authtoken("Jimbo", "69");

        try{
            AuthDAO.insertAuth(t);
            AuthDAO.removeToken("69");
        } catch(DataAccessException e){
            Assertions.assertNull(e.getMessage());
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex);
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

    }

    @Test
    @DisplayName("Fail To Add User to Database")
    public void AddUserFail(){

    }

    @Test
    @DisplayName("Successfully Clear User Database")
    public void ClearUser(){

    }

    @Test
    @DisplayName("Successfully Check If User Is In Database")
    public void CheckUser(){

    }

    @Test
    @DisplayName("Fail Checking If User Is In Database")
    public void CheckUserFail(){

    }

    @Test
    @DisplayName("Successfully Login User")
    public void LoginUser(){

    }

    @Test
    @DisplayName("Fail To Login User")
    public void LoginUserFail(){

    }

    @Test
    @DisplayName("Successfully Add Game to Database")
    public void AddGameSuccess(){

    }

    @Test
    @DisplayName("Fail To Add Game to Database")
    public void AddGameFail(){

    }

    @Test
    @DisplayName("Successfully Clear Game Database")
    public void ClearGames(){

    }

    @Test
    @DisplayName("Successfully List All Games")
    public void ListGame(){

    }

    @Test
    @DisplayName("Fail To List All Games")
    public void ListGameFail(){

    }

    @Test
    @DisplayName("Successfully Join A Games")
    public void JoinGame(){

    }

    @Test
    @DisplayName("Fail To Join A Games")
    public void JoinGameFail(){

    }
}
