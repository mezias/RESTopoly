package resttopoly.models.responemodel;

public class GameResponse
{
    private String id;
    
    private String name;
    
    private String players;
    
    private String components;
    
    private String services;
    
    private String status;

    public GameResponse(String id, String name, String players,
            String components, String services, String status)
    {
        this.id = id;
        this.name = name;
        this.players = players;
        this.components = components;
        this.services = services;
        this.status = status;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPlayers()
    {
        return players;
    }

    public void setPlayers(String players)
    {
        this.players = players;
    }

    public String getComponents()
    {
        return components;
    }

    public void setComponents(String components)
    {
        this.components = components;
    }

    public String getServices()
    {
        return services;
    }

    public void setServices(String services)
    {
        this.services = services;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
