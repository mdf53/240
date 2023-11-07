package handlers;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import results.ClearResult;
import services.ClearService;

/**
 * Converts an HTTP request into usable Java objects and data.
 */
public class ClearHandler extends BaseHandler{
    public Object handleRequest(spark.Request request, spark.Response response) throws DataAccessException {
        ClearService service = new ClearService();
        ClearResult res = service.clear();
        return new Gson().toJson(res);
    }
}

