package resttopoly.contexts;

import resttopoly.models.Player;
import resttopoly.models.User;

/**
 * @author DucNguyenMinh
 * @since 31/05/16
 */
public class MutexContext
{
    private Player currentPlayer;

    private boolean isAcquired;


    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public boolean acquire(Player player)
    {
        if (!isAcquired) {
            this.currentPlayer = player;
            this.isAcquired = true;
            return true;
        }

        return false;
    }

    public boolean release(Player player)
    {
        if (player == null){
            return false;
        }

        if (this.currentPlayer == null){
            return true;
        }

        if (player.getId().equals(this.currentPlayer.getId())) {
            this.currentPlayer = null;
            this.isAcquired = false;
            return true;
        }

        return false;
    }

    public boolean isAcquired()
    {
        return isAcquired;
    }
}
