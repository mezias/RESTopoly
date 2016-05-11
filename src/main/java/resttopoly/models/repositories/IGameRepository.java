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
public interface IGameRepository extends Repository
{
    List<Game> findAllGames();
    Game createGame(Game game) throws CannotCreateException;
    Game findGame(String id);

}
