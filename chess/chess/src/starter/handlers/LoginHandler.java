package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import requests.LoginRequest;
import requests.RegisterRequest;
import results.LoginResult;
import results.RegisterResults;
import services.LoginService;
import services.RegisterService;

import java.util.Objects;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class LoginHandler extends BaseHandler {

    public Object handleRequest(spark.Request req, spark.Response response){
        Gson gson = new Gson();
        //make new service object
        LoginRequest request = gson.fromJson(req.body(), LoginRequest.class);
        LoginResult result = new LoginResult(null);

            LoginService service = new LoginService();
            result = service.login(request);
            if(result.getMessage() == null){
                response.status(200);
            } else if (Objects.equals(result.getMessage(), "Error: unauthorized")){
                response.status(401);
            } else{
                response.status(500);
            }


        return gson.toJson(result);
    }
}
