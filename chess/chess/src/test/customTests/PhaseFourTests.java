package customTests;

import models.*;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;
import services.ClearService;

public class PhaseFourTests {

    @Test
    @DisplayName("Successfully Add AuthToken to Database")
    public void AddToAuthDataSuccess(){
        Authtoken t = new Authtoken("Jimbo", "69");
        try{
            AuthDAO.insertAuth(t);
        }catch(DataAccessException e){
            Assertions.assertEquals("a","a");
        }
    }

    @Test
    @DisplayName("Fail To Add AuthToken to Database")
    public void AddToAuthDataFail(){

    }

    @Test
    @DisplayName("Clear the AuthToken Database")
    public void ClearAuthData(){

    }

    @Test
    @DisplayName("Successfully Remove a Token From the AuthToken Database")
    public void RemoveAuthData(){

    }

    @Test
    @DisplayName("Fail To Remove a Token From the AuthToken Database")
    public void RemoveAuthDataFail(){

    }

    @Test
    @DisplayName("Successfully Validate Token")
    public void ValidateToken(){

    }

    @Test
    @DisplayName("Fail To Validate Token")
    public void ValidateTokenFail(){

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
