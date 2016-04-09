package resttopoly.handlers;

import org.eclipse.jetty.server.handler.AbstractHandler;
import resttopoly.Answer;
import resttopoly.model.Roll;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Map;
import java.util.Random;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class DiceRollHandler extends AbstractRequestHandler implements RequestHandler, Route
{
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_OK = 200;

    @Override
    public Answer process(Map<String, String> urlParams)
    {
        int roll = roll();
        String json = dataToJson(new Roll(roll));
        return new Answer(HTTP_OK, json);
    }


    private int roll(){
        Random random = new Random();
        int roll = random.nextInt(6) + 1;
        return roll;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception
    {
            Map<String, String> urlParams = request.params();

            Answer answer = process(urlParams);
            response.type("application/json");
            response.status(answer.getCode());
            response.body(answer.getBody());

            return answer.getBody();
    }

}
