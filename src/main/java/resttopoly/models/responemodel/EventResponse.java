package resttopoly.models.responemodel;

import java.sql.Timestamp;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public class EventResponse
{
    private String id;

    private String game;

    private String type;

    private String name;

    private String reason;

    private String resource;

    private String player;

    private String time;

    public EventResponse(String id, String game, String type, String name, String reason, String resource, String player, String time)
    {
        this.id = id;
        this.game = game;
        this.type = type;
        this.name = name;
        this.reason = reason;
        this.resource = resource;
        this.player = player;
        this.time = time;
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

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
