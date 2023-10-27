import handlers.*;
import spark.Route;
import spark.Spark;

import java.util.Locale;

public class Server {
//    public static void main(String[] args) {
//        try {
//            int port = Integer.parseInt(args[0]);
//            Spark.port(port);
//
//            new Server().run();
//
//            Spark.awaitInitialization();
//            System.out.println("Listening on port " + port);
//        } catch(ArrayIndexOutOfBoundsException | NumberFormatException ex) {
//            System.err.println("Specify the port number as a command line parameter");
//        }
//    }
//
//    private void run(){
//        Spark.staticFiles.location("src/web");
//

//        Spark.delete("/db", (req,res) -> (new ClearHandler()).handleRequest(req,res));
//        //if HTTP is POST, login. if DELETE logout
//        Spark.post("/session", (req,res) -> (new LoginHandler()).handleRequest(req,res));
//        Spark.delete("/session", (req,res) -> (new LogoutHandler()).handleRequest(req,res));
//        //if HTTP is GET, list games. if POST create game, if PUT join game
////        Spark.post("/game", (req,res) -> (new CreateGameHandler()).handleRequest(req,res));
////        Spark.get("/game", (req,res) -> (new ListGameHandler()).handleRequest(req,res));
////        Spark.put("/game", (req,res) -> (new JoinGameHandler()).handleRequest(req,res));
//
//
//
//
//    }
public static void main(String[] args) {
    new Server().run();
}

    private void run() {
        Spark.port(8080);
        Spark.externalStaticFileLocation("src/web");

        //API functions
        Spark.delete("/db", (req,res) -> (new ClearHandler()).handleRequest(req,res));
        Spark.post("/user", (req, res) -> (new RegisterHandler()).handleRequest(req, res));
        Spark.post("/session", (req,res) -> (new LoginHandler()).handleRequest(req,res));
        Spark.delete("/session", new LogoutHandler());
        Spark.get("/game", new ListGameHandler());
        Spark.post("/game", new CreateGameHandler());
        Spark.put("/game", new JoinGameHandler());
    }

}
