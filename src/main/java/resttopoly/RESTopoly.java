package resttopoly;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.sql2o.Sql2o;
import resttopoly.handlers.DiceRollHandler;
import resttopoly.handlers.EventHandler;
import resttopoly.handlers.UserHandler;
import resttopoly.handlers.transformer.JsonTransformer;
import resttopoly.models.repositories.*;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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


        // Events Service
        IEventRepository eventRepository = new EventRepository_with_Map(new HashMap<>());
        EventHandler eventHandler = new EventHandler(eventRepository) ;
        get("/events",(request, response) -> eventHandler.findEvents(request,response),jsonTransformer);
        post("/events",((request, response) -> eventHandler.createEvent(request,response)),jsonTransformer);
        delete("/events",((request, response) -> eventHandler.deleteEvents(request,response)));

        get("/events/:eventid",((request, response) -> eventHandler.findEvent(request,response,request.params(":eventid"))),jsonTransformer);
    }
}
