package resttopoly.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
@Data
public class Roll implements Serializable
{
    private int number;
    private String player;
    private String game;

    public Roll(int number, String player, String game)
    {
        this.number = number;
        this.player = player;
        this.game = game;
    }

    public static Roll createRoll(String player, String game)
    {
        Random random = new Random(System.currentTimeMillis());
        int roll = random.nextInt(6) + 1;
        return new Roll(roll,player,game);
    }
}
