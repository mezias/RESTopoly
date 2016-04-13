package resttopoly.handlers;

import org.eclipse.jetty.http.HttpStatus;
import resttopoly.Answer;
import resttopoly.models.Roll;
import spark.Request;
import spark.Response;
import spark.Route;
import sun.net.www.http.HttpClient;

import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class DiceRollHandler extends AbstractRequestHandler implements Route
{
    @Override
    public Answer process(Map<String, String> urlParams)
    {
        return null;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception
    {
        String player = request.queryParams("player");
        String game = request.queryParams("game");
        Roll roll = Roll.createRoll(player,game);

        response.type("application/json");
        response.status(HttpStatus.OK_200);
        return roll;
    }

}
