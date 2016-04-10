package resttopoly;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.sql2o.Sql2o;
import resttopoly.handlers.DiceRollHandler;
import resttopoly.handlers.UserHandler;
import resttopoly.handlers.transformer.JsonTransformer;
import resttopoly.model.User;
import resttopoly.model.repositories.UserRepository;

import javax.sql.DataSource;

import static spark.Spark.*;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class RESTopoly
{
    private static DataSource mysqlDataSource()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/RESTopoly");
        dataSource.setUser("root");
        dataSource.setPassword("123456789");
        return dataSource;
    }

    public static void main(String args[])
    {
        JsonTransformer jsonTransformer = new JsonTransformer();
        UserRepository userRepository = new UserRepository(new Sql2o(mysqlDataSource()));
        UserHandler userHandler = new UserHandler(userRepository);

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

    }
}
