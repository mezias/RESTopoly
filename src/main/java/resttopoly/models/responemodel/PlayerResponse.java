package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Player;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
@Data
public class PlayerResponse
{
    private static final String BASE_URL = "/games";

    private String id;

    private String user;

    private String pawn;

    private String account;

    private String ready;

    public PlayerResponse(String gameId ,Player player)
    {
        this.id = BASE_URL+"/"+gameId+"/players/"+player.getId();
        this.user = player.getUser();
        this.pawn = player.getPawn();
        this.account = player.getAccount();
        this.ready = BASE_URL+"/"+gameId+"/players/"+player.getId()+"/ready";
    }
}
