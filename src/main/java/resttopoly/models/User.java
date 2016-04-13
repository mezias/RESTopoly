package resttopoly.models;

import lombok.Data;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
@Data
public class User
{
    private String name;

    private String uri;

    public User(String name)
    {
        this.name = name;
    }

    public User(String name, String uri)
    {
        this.name = name;
        this.uri = uri;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
