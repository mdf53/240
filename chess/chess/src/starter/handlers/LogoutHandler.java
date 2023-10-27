package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import requests.RegisterRequest;
import results.LogoutResult;
import results.RegisterResults;
import services.LogoutService;
import services.RegisterService;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class LogoutHandler extends BaseHandler{
    public Object handleRequest(spark.Request request, spark.Response response){
        //make new gson object
        Gson gson = new Gson();
        LogoutResult result = new LogoutResult(null);
        try{
            LogoutService service = new LogoutService();
            result = service.logout(request.headers("Authorization"));
            if(result.getMessage() == null){
                response.status(200);
            } else if (Objects.equals(result.getMessage(), "Error: unauthorized")){
                response.status(401);
            } else{
                response.status(500);
            }
        } catch(DataAccessException ex){
            response.status(400);
        }

        return gson.toJson(result);
    }
}
