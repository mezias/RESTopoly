package resttopoly;

import resttopoly.handlers.DiceRollHandler;

import static spark.Spark.get;
/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class RESTopoly
{
    public static void main(String args[])
    {
        get("/dice", new DiceRollHandler());
    }
}
