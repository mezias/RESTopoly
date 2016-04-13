package resttopoly.models.ModelForm;

/**
 * @author DucNguyenMinh
 * @since 10/04/16
 */
public class UserForm
{
    public UserForm(String name, String uri)
    {
        this.name = name;
        this.uri = uri;
    }

    private String name;
    private String uri;

    protected UserForm(){}
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
