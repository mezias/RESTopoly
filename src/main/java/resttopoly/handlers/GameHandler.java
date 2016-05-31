package resttopoly.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.glassfish.external.probe.provider.PluginPoint;
import org.eclipse.jetty.http.HttpStatus;

import resttopoly.logiccontroller.GameController;
import resttopoly.models.*;
import resttopoly.models.ModelForm.*;
import resttopoly.models.repositories.CannotCreateException;
import resttopoly.models.repositories.IGameRepository;
import resttopoly.models.responemodel.*;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            GameResponse gameResponse = new GameResponse(game);
            response.status(HttpStatus.OK_200);
            return gameResponse;
        }
    }

    public GamesResponse getAllGames(Request request, Response response)
    {
        List<Game> gameList = gameRepository.findAllGames();
        List<String> gameIds = new ArrayList<>();

        for (Game game :
                gameList) {
            gameIds.add(game.getId());
        }

        response.status(HttpStatus.OK_200);

        return new GamesResponse(gameIds);
    }

    public GameResponse createGames(Request request, Response response)
    {
        GameForm gameForm;

        try {
            gameForm = new ObjectMapper().readValue(request.body(), GameForm.class);
            Game game = new Game(gameForm.getName(), gameForm.getServices());
            gameRepository.createGame(game);
            game.setGameController(new GameController(game.getId(), gameForm.getServices()));
            response.status(HttpStatus.CREATED_201);
            return new GameResponse(game);
        } catch (IOException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        } catch (CannotCreateException e) {
            response.status(HttpStatus.CONFLICT_409);
            e.printStackTrace();
        }

        return null;
    }

    public GameResponse getGame(String gameid, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameid);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {
            GameResponse responseObj = new GameResponse(game);
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }

    public StatusResponse getStatusOfGame(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {
            StatusResponse responseObj = new StatusResponse(game.getStatus().toString());
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }

    public ServicesResponse getServices(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {
            ServicesResponse responseObj = new ServicesResponse(game.getServices());
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }

    public ServicesResponse updateServices(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {

            try {
                ServicesForm servicesForm = new ObjectMapper().readValue(request.body(), ServicesForm.class);
                Services services = servicesForm.getServices();
                game.updateServices(services);
                response.status(HttpStatus.OK_200);
                return new ServicesResponse(game.getServices());
            } catch (IOException e) {
                response.status(HttpStatus.BAD_REQUEST_400);
                e.printStackTrace();
            }

            ServicesResponse responseObj = new ServicesResponse(game.getServices());
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }

    public ComponentsResponse getComponents(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {
            ComponentsResponse responseObj = new ComponentsResponse(game.getComponents());
            response.status(org.apache.http.HttpStatus.SC_OK);
            return responseObj;
        }
    }

    public ComponentsResponse updateComponents(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        } else {

            try {
                ComponentsForm componentsForm = new ObjectMapper().readValue(request.body(), ComponentsForm.class);
                Components components = componentsForm.getComponents();
                game.updateComponents(components);
                response.status(HttpStatus.OK_200);
                return new ComponentsResponse(game.getComponents());
            } catch (IOException e) {
                response.status(HttpStatus.BAD_REQUEST_400);
                e.printStackTrace();
            }
        }

        return null;
    }

    public PlayersResponse getAllPlayers(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        List<Player> players = game.getPlayers();

        response.status(HttpStatus.OK_200);

        return new PlayersResponse(gameId,players);
    }

    public PlayerResponse createPlayer(String gameId, Request request, Response response)
    {


        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        try {
            PlayerForm playerForm = new ObjectMapper().readValue(request.body(),PlayerForm.class);
            Player player = new Player(playerForm.getUser(),playerForm.isReady());
            game.addPlayerToGame(player);
            response.status(HttpStatus.OK_200);
            return new PlayerResponse(gameId,game.getPlayer(player.getId()));
        } catch (IOException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        } catch (CannotCreateException e) {
            response.status(HttpStatus.CONFLICT_409);
            e.printStackTrace();
        }

        return null;
    }

    public PlayerResponse getPlayer(String gameId, String playerId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }

        response.status(HttpStatus.OK_200);
        return new PlayerResponse(gameId,player);
    }

    public String deletePlayer(String gameId, String playerId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }

        game.deletePlayer(player);

        response.status(HttpStatus.OK_200);
        return "Player deleted!";
    }

    public PlayerResponse updatePlayer(String gameId, String playerId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }

        try {
            PlayerUpdateForm playerForm = new ObjectMapper().readValue(request.body(),PlayerUpdateForm.class);
            Player updatedPlayer = new Player(player.getUser(),player.getReady());
            updatedPlayer.setPawn(playerForm.getPawn());
            updatedPlayer.setAccount(playerForm.getAccount());
            game.updatePlayer(updatedPlayer);
        } catch (IOException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        }


        response.status(HttpStatus.OK_200);
        return new PlayerResponse(gameId,player);
    }

    public ReadyResponse getReady(String gameId, String playerId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }
        
        response.status(HttpStatus.OK_200);
        return new ReadyResponse(player);
    }

    public ReadyResponse updateReady(String gameId, String playerId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player player = game.getPlayer(playerId);
        if (player == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }
        
        try {
            PlayerUpdateForm playerForm = new ObjectMapper().readValue(request.body(), PlayerUpdateForm.class);
            Player updatedPlayer = new Player(player.getUser(),player.getReady());
            updatedPlayer.setReady(playerForm.isReady());
            game.updatePlayer(updatedPlayer);
            System.out.println("test");
        } catch (IOException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
            e.printStackTrace();
        }

        response.status(HttpStatus.OK_200);
        return new ReadyResponse(player);
    }

    public PlayerResponse aquireMutex(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        String playerURI = request.queryParams("player");
        String[] playerURIarr = playerURI.split("/");

        if (playerURIarr.length != 5) {
            response.status(org.apache.http.HttpStatus.SC_BAD_REQUEST);
            response.body("PLayer or game does not exist!");
            return null;
        }

        String reqBdGameID = playerURIarr[2];

        Game reqBdGame = gameRepository.findGame(reqBdGameID);

        if (reqBdGame == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player aquiringPlayer = game.getPlayer(playerURIarr[4]);

        if (aquiringPlayer == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such player!");
            return null;
        }

        Player holdingMutexPlayer = game.getCurrentInTurnPlayer();


        if ( holdingMutexPlayer != null && holdingMutexPlayer.getId().equals(aquiringPlayer.getId())){
            response.status(HttpStatus.OK_200);
            return new PlayerResponse(gameId,aquiringPlayer);
        }

        boolean acquired = game.acquireMutex(aquiringPlayer);

        if (acquired){
            response.status(HttpStatus.CREATED_201);
            return new PlayerResponse(gameId,aquiringPlayer);
        } else {
            response.status(org.apache.http.HttpStatus.SC_CONFLICT);
            response.body("Already aquired by another Player!");
            return null;
        }
    }

    public PlayerResponse getCurrentActivePlayer(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        Player currentPlayer = game.getCurrentInTurnPlayer();

        if (currentPlayer == null) {
            response.status(HttpStatus.NOT_FOUND_404);
            response.body("There is no active Player!");
            return null;
        }

        response.status(HttpStatus.OK_200);
        return new PlayerResponse(gameId,currentPlayer);
    }

    public String releaseMutex(String gameId, Request request, Response response)
    {
        Game game = gameRepository.findGame(gameId);

        if (game == null) {
            response.status(org.apache.http.HttpStatus.SC_NOT_FOUND);
            response.body("There is no such game!");
            return null;
        }

        boolean released = game.releaseMutex();

        if (released) {
            response.status(HttpStatus.OK_200);
            return "Mutex released!";
        } else {
            response.status(HttpStatus.CONFLICT_409);
            return "Mutex cannot be released";
        }
    }
}
