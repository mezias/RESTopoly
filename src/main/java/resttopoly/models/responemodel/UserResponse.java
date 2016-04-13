package resttopoly.models.responemodel;

/**
 * @author DucNguyenMinh
 * @since 10/04/16
 */
public class UserResponse
{
    private String id;
    private String name;
    private String uri;
    private String basepath;

    public UserResponse(String basepath, String name, String uri)
    {
        this.basepath = basepath;
        this.id = this.basepath+"/"+name;
        this.name = name;
        this.uri = uri;
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

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
