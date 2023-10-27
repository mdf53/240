package handlers;

import dataAccess.DataAccessException;
import requests.RegisterRequest;
import results.RegisterResults;
import com.google.gson.Gson;
import services.RegisterService;
import spark.Spark;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class RegisterHandler extends BaseHandler{
    public Object handleRequest(spark.Request request, spark.Response response){
        //make new gson object
        Gson gson = new Gson();
        //make new service object
        RegisterRequest regRequest = gson.fromJson(request.body(), RegisterRequest.class);
        RegisterResults result = new RegisterResults(null);
        try{
            RegisterService service = new RegisterService();
            result = service.register(regRequest);
            if(result.getMessage() == null){
                response.status(200);
            } else if (Objects.equals(result.getMessage(), "Error: bad request")){
                response.status(400);
            } else if (Objects.equals(result.getMessage(), "Error: already taken")){
                response.status(403);
            } else{
                response.status(500);
            }
        } catch(DataAccessException ex){
            response.status(400);
        }

        return gson.toJson(result);
    }
}
