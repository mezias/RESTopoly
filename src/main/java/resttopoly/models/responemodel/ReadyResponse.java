package resttopoly.models.responemodel;

import resttopoly.models.Player;

public class ReadyResponse
{
    private boolean ready;

    public ReadyResponse(Player player) {
        this.ready = player.getReady();
    }
}
