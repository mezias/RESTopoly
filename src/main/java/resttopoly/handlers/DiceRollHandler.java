package resttopoly.handlers;

import org.eclipse.jetty.http.HttpStatus;
import resttopoly.Answer;
import resttopoly.services.EventServiceProvider;
import resttopoly.services.YellopagesService;
import resttopoly.models.Roll;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class DiceRollHandler extends AbstractRequestHandler implements Route
{
    private static final String EVENT_URL = "http://172.18.0.47:4567/events";

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

        createEvent(player,game);

        response.type("application/json");
        response.status(HttpStatus.OK_200);
        return roll;
    }

    private String createEvent(String player, String game){
        HttpURLConnection connection = null;

        try{
            YellopagesService eventService = EventServiceProvider.getService();
            URL url = new URL(eventService.getUri()+"events");

            String requestBody = "{ " +
                    "\"game\":\"" + game +" \", "+
                    "\"player\":\"" + player + "\"" +
                    "}";

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Content-Length","" +requestBody.getBytes().length);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(requestBody);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();

            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
                response.append('\r');
            }

            reader.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }

        return "";
    }

}
