package handlers;

import spark.Request;
import spark.Response;
import spark.Route;

public class BaseHandler implements Route {
    public boolean validateAuthToken(String authToken){
        return false;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        //be able to validate auth tokens.


        return null;
    }
}
