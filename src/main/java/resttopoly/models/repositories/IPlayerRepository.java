package resttopoly.models.repositories;

import resttopoly.models.Player;

import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public interface IPlayerRepository extends Repository
{
    void createPlayerListForGame(String gameId);

    List<Player> findAllPlayers(String gameId);

    Player findPlayer(String gameId, String id);

    Player createPlayer(String gameId, Player player);

    void deletePlayer(String gameId, Player player);
}
