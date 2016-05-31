package resttopoly;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.sql2o.Sql2o;
import resttopoly.handlers.DiceRollHandler;
import resttopoly.handlers.EventHandler;
import resttopoly.handlers.GameHandler;
import resttopoly.handlers.UserHandler;
import resttopoly.handlers.transformer.JsonTransformer;
import resttopoly.models.Game;
import resttopoly.models.Player;
import resttopoly.models.Services;
import resttopoly.models.repositories.*;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static spark.Spark.*;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class RESTopoly
{
//    private static DataSource mysqlDataSource() throws FileNotFoundException
//    {
//        Properties properties = new Properties();
//        MysqlDataSource dataSource = new MysqlDataSource();
//        try {
//            properties.load(new FileInputStream( "src/main/db_config.properties"));
//            String url = properties.getProperty("url");
//            dataSource.setUrl(url);
//            String user = properties.getProperty("user");
//            dataSource.setUser(user);
//            String password = properties.getProperty("password");
//            dataSource.setPassword(password);
//        } catch (IOException e) {
//        }
//
//        return dataSource;
//    }

    public static void main(String args[])
    {
        JsonTransformer jsonTransformer = new JsonTransformer();
//        IUserRepository IUserRepository = null;
//        try {
//            IUserRepository = new UserRepositoryWithDatabase(new Sql2o(mysqlDataSource()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        registerRepositories();

        GameHandler gameHandler = new GameHandler(new GameRepositoryWithMap(new HashMap<>(), new HashMap<>()));

        UserHandler userHandler = new UserHandler(new UserRespositoryWithMap(new HashMap<>()));

        //home
        get("/", ((request, response) -> "Hello!" ));


        // dice
        get("/dice", new DiceRollHandler(), jsonTransformer);

        // Users
        get("/users/:username", (request, response) -> userHandler.findUser(request.params(":username"), request, response)
        , jsonTransformer);
        put("/users/:username", (request, response) ->
             userHandler.saveOrUpdate(request.params(":username"),request,response)
        );
        delete("/users/:username",((request, response) -> userHandler.deleteUser(request.params(":username"),request,response)));
        get("/users", ((request, response) -> userHandler.getAllUsers(request,response)),jsonTransformer);
        post("/users", "application/json",(((request, response) -> userHandler.createUser(request,response))));

        //Games
        get("/games", (request, response) -> gameHandler.getAllGames(request,response), jsonTransformer);
        post("/games", (request, response) -> gameHandler.createGames(request,response), jsonTransformer );

        //GAMES{GAMEID}
        get("/games/:gameId", (request, response) -> gameHandler.getGame(request.params(":gameId"), request,response), jsonTransformer );

        //GAMES{GAMEID}STATUS
        get("/games/:gameId/status", (request, response) -> gameHandler.getStatusOfGame(request.params(":gameId"), request,response),jsonTransformer);
//        put
        //GAMES{GAMEID}SERVICES
        get("/games/:gameId/services", (request, response) -> gameHandler.getServices(request.params(":gameId"), request,response),jsonTransformer);
        put("/games/:gameId/services", (request, response) -> gameHandler.updateServices(request.params(":gameId"), request,response),jsonTransformer);

        //GAMES{GAMEID}COMPONENTS
        get("/games/:gameId/components", (request, response) -> gameHandler.getComponents(request.params(":gameId"), request,response),jsonTransformer);
        put("/games/:gameId/components", (request, response) -> gameHandler.updateComponents(request.params(":gameId"), request,response),jsonTransformer);

        //GAMES{GAMEID}PLAYERS
        get("/games/:gameId/players", (request, response) -> gameHandler.getAllPlayers(request.params(":gameId"), request,response),jsonTransformer);
        post("/games/:gameId/players", (request, response) -> gameHandler.createPlayer(request.params(":gameId"), request,response),jsonTransformer);

        //GAMES{GAMEID}PLAYERS/CURRENT
        get("/games/:gameId/players/current",(request, response) -> gameHandler.getCurrentActivePlayer(request.params(":gameId"), request,response),jsonTransformer);

        //GAMES{GAMEID}PLAYERS/TURN
        get("/games/:gameId/players/turn", (request, response) -> gameHandler.getCurrentActivePlayer(request.params(":gameId"), request,response), jsonTransformer);
        put("/games/:gameId/players/turn", (request, response) -> gameHandler.aquireMutex(request.params(":gameId"), request,response), jsonTransformer);
        delete("/games/:gameId/players/turn", (request, response) -> gameHandler.releaseMutex(request.params(":gameId"), request,response), jsonTransformer);

        //GAMES{GAMEID}PLAYERS{PLAYERID}
        get("/games/:gameId/players/:playerId", (request, response) -> gameHandler.getPlayer(request.params(":gameId"),request.params(":playerid"), request,response),jsonTransformer);
        put("/games/:gameId/players/:playerId", (request, response) -> gameHandler.updatePlayer(request.params(":gameId"),request.params(":playerid"), request,response),jsonTransformer);
        delete("/games/:gameId/players/:playerId", (request, response) -> gameHandler.deletePlayer(request.params(":gameId"),request.params(":playerid"), request,response),jsonTransformer);

        //GAMES{GAMEID}PLAYERS{PLAYERID}READY
        get("/games/:gameId/players/:playerId/ready", (request, response) -> gameHandler.getReady(request.params(":gameId"),request.params(":playerid"), request,response),jsonTransformer);
        put("/games/:gameId/players/:playerId/ready", (request, response) -> gameHandler.updateReady(request.params(":gameId"),request.params(":playerid"), request,response),jsonTransformer);

        // Events Service
        IEventRepository eventRepository = new EventRepository_with_Map(new HashMap<>());
        EventHandler eventHandler = new EventHandler(eventRepository) ;
        get("/events",(request, response) -> eventHandler.findEvents(request,response),jsonTransformer);
        post("/events",((request, response) -> eventHandler.createEvent(request,response)),jsonTransformer);
        delete("/events",((request, response) -> eventHandler.deleteEvents(request,response)));

        get("/events/:eventid",((request, response) -> eventHandler.findEvent(request,response,request.params(":eventid"))),jsonTransformer);
    }

    private static void registerRepositories()
    {
        RepositoryProvider.register(IGameRepository.class, new GameRepositoryWithMap(new HashMap<Long, Game>(), new HashMap<String, Player>()));
        RepositoryProvider.register(IPlayerRepository.class, new PlayerRepository(new HashMap<String, Map<String, Player>>()));
        RepositoryProvider.register(IServiceRepository.class, new ServiceRepository(new HashMap<String, Services>()));
        RepositoryProvider.register(IComponentRepository.class, new ComponentRepository(new HashMap<>()));

    }
}
