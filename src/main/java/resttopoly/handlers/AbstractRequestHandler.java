package resttopoly.handlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import resttopoly.Answer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public abstract class AbstractRequestHandler implements RequestHandler, Route
{


    @Override
    public abstract Answer process(Map<String, String> urlParams);


    @Override
    public Object handle(Request request, Response response) throws Exception
    {
        return null;
    }
    public static String dataToJson(Object data) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
}
