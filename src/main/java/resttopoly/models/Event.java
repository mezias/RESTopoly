package resttopoly.models;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
@Data
public class Event
{
    private String id;

    private String game;

    private String type;

    private String name;

    private String reason;

    private String resource;

    private String player;

    private Timestamp time;

    public Event(String game, String player,String type, String name, String reason)
    {
        this.game = game;
        this.type = type;
        this.name = name;
        this.reason = reason;
        this.resource = "";
        this.player = "";
        long timeInMilis = System.currentTimeMillis();
        this.time = new Timestamp(timeInMilis);
        this.id = "/games/"+game+"/players/"+player+"/events/"+timeInMilis;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getGame()
    {
        return game;
    }

    public void setGame(String game)
    {
        this.game = game;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getResource()
    {
        return resource;
    }

    public void setResource(String resource)
    {
        this.resource = resource;
    }

    public String getPlayer()
    {
        return player;
    }

    public void setPlayer(String player)
    {
        this.player = player;
    }

    public Timestamp getTime()
    {
        return time;
    }

    public void setTime(Timestamp time)
    {
        this.time = time;
    }
}
