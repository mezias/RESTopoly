package resttopoly.models.repositories;

import java.util.List;
import resttopoly.models.Game;
import resttopoly.models.Player;
import resttopoly.models.Services;
import resttopoly.models.Components;

/**
 * @author GerritBode
 * @since 06/05/16
 */
public interface IGameRepository
{
    List<Game> findAllGames();
    Game createGame(Game game) throws CannotCreateException;
    Game findGame(String id);
    String getStatus(String name);
    String setStatus(String status);
    Services getService();
    void setService(String service);
    Components getComponents();
    void setComponent(String component);
    List<String> findAllPlayers();
    Player createNewPlayer(String id, String user, String pawn, String account, String ready);
    Player findPlayer(String id);
    void putPlayer(Player player);
    void deletePlayer(String id);
    String setPlayerReady(String id);
    String getReady();
    Player getCurrentPlayer();
    String getPlayersTurn();
    String tryTakeTurn();
    void endTurn();
}
