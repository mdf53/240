package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import requests.CreateGameRequest;
import results.CreateGameResult;
import services.CreateGameService;
import spark.Request;
import spark.Response;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class CreateGameHandler extends BaseHandler{
    public Object handleRequest(Request req, Response res) {
        Gson gson = new Gson();

        CreateGameRequest request = gson.fromJson(req.body(), CreateGameRequest.class);
        CreateGameResult result = new CreateGameResult();
        try{
            CreateGameService service = new CreateGameService();
            result = service.createGame(request, req.headers("Authorization"));
            if(result.getMessage() == null){
                res.status(200);
            } else if (Objects.equals(result.getMessage(), "Error: bad request")){
                res.status(400);
            } else if (Objects.equals(result.getMessage(), "Error: unauthorized")){
                res.status(401);
            }
        } catch(DataAccessException exception){
            res.status(400);
        }


        return gson.toJson(result);
    }
}
