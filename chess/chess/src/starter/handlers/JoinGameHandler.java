package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import requests.JoinGameRequest;

import results.JoinGameResult;
import services.JoinGameService;
import spark.Request;
import spark.Response;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class JoinGameHandler extends BaseHandler{
    public Object handleRequest(spark.Request request, spark.Response response){
        //make new gson object
        Gson gson = new Gson();
        //make new service object
        JoinGameRequest regRequest = gson.fromJson(request.body(), JoinGameRequest.class);
        JoinGameResult result = new JoinGameResult(null);
            JoinGameService service = new JoinGameService();
            result = service.joinGame(regRequest, request.headers("Authorization"));
            if(result.getMessage() == null){
                response.status(200);
            } else if (Objects.equals(result.getMessage(), "Error: bad request")){
                response.status(400);
            } else if (Objects.equals(result.getMessage(), "Error: unauthorized")){
                response.status(401);
            }else if (Objects.equals(result.getMessage(), "Error: already taken")){
                response.status(403);
            } else{
                response.status(500);
            }

        return gson.toJson(result);
    }
}
