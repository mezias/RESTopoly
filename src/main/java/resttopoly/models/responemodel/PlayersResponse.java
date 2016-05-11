package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
@Data
public class PlayersResponse
{
    private static final String BASE_URI = "/games/";

    private List<String> players;

    public PlayersResponse(String gameId ,List<Player> players)
    {

        this.players = new ArrayList<>();

        for (Player player :
               players ) {
            String id = BASE_URI+gameId+"/players/"+player.getId() ;
            this.players.add(id);
        }

    }
}
