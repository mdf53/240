package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import results.ListGamesResults;
import results.LogoutResult;
import services.ListGamesService;
import services.LogoutService;
import spark.Request;
import spark.Response;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class ListGameHandler extends BaseHandler{
    public Object handleRequest(spark.Request request, spark.Response response){
        //make new gson object
        Gson gson = new Gson();
        ListGamesResults result = new ListGamesResults(null);
        try{
            ListGamesService service = new ListGamesService();
            result = service.listGames(request.headers("Authorization"));
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
    }}
