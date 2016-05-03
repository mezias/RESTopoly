package resttopoly.models.responemodel;

import lombok.Data;

/**
 * @author DucNguyenMinh
 * @since 20/04/16
 */
@Data
public class Service
{
    private String name;
    private String service;
    private String uri;
    private String status;
    private String description;

    public String getName()
    {
        return name;
    }

    public String getService()
    {
        return service;
    }

    public String getUri()
    {
        return uri;
    }

    public String getStatus()
    {
        return status;
    }

    public String getDescription()
    {
        return description;
    }
}
