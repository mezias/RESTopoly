package resttopoly.models.repositories;

import resttopoly.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class PlayerRepository implements IPlayerRepository
{
    private Map<String, Map<String, Player>> playersMap;

    public PlayerRepository(Map<String, Map<String, Player>> playersMap)
    {
        this.playersMap = playersMap;
    }

    @Override
    public void createPlayerListForGame(String gameId)
    {
        if(!playersMap.containsKey(gameId)){
            playersMap.put(gameId,new HashMap<>());
        }
    }

    @Override
    public List<Player> findAllPlayers(String gameId)
    {
        List<Player> places = new ArrayList<>();
        if (playersMap.get(gameId) != null) {
            for (Map.Entry<String, Player> entry :
                    playersMap.get(gameId).entrySet()) {
                places.add(entry.getValue());
            }
        }
        return places;
    }

    @Override
    public Player findPlayer(String gameId, String id)
    {
        Map<String, Player> players = playersMap.get(gameId);

        if( players == null ){
            return null;
        }

        Player player = players.get(id);

        return player;
    }

    @Override
    public Player createPlayer(String gameId, Player player)
    {
        Map<String, Player> players = playersMap.get(gameId);

        if( players == null ){
            return null;
        }

        players.put(player.getId(),player);

        return player;
    }

    @Override
    public void deletePlayer(String gameId, Player player)
    {
        Map<String, Player> players = playersMap.get(gameId);

        if( players != null ){
            players.remove(player.getId());
        }
    }
}
