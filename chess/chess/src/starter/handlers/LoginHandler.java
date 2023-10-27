package handlers;

import com.google.gson.Gson;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import requests.LoginRequest;
import results.LoginResult;
import services.LoginService;
/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class LoginHandler extends BaseHandler {

    public spark.Response handleRequest(spark.Request req, spark.Response response){
        LoginRequest request = (LoginRequest) Gson.fromJson(req.body(), LoginRequest.class);

        LoginService service = new LoginService();
        LoginResult result = service.login(request);

        return Gson.toJson(result);
//        return null;
    }
}
