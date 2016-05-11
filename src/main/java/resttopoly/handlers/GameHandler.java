package resttopoly.handlers;

import org.eclipse.jetty.http.HttpStatus;

import resttopoly.models.Game;
import resttopoly.models.User;
import resttopoly.models.repositories.IGameRepository;
import resttopoly.models.responemodel.GameResponse;
import resttopoly.models.responemodel.UserResponse;
import spark.Request;
import spark.Response;

public class GameHandler
{
    private IGameRepository gameRepository;

    private static final String BASE_PATH = "/games";

    public GameHandler(IGameRepository IGameRepository)
    {
        this.gameRepository = IGameRepository;
    }
    
    public GameResponse findGame(String name, Request request, Response response)
    {
        Game game = gameRepository.findGame(name);


        if (game == null) {
            response.status(HttpStatus.NOT_FOUND_404);
            return null;
        } else {
            GameResponse gameResponse = new GameResponse(game.getId(), game.getName(), game.getPlayers(),
                    game.getComponents(), game.getServices(), game.getStatus());
            response.status(HttpStatus.OK_200);
            return gameResponse;
        }
    }
}
