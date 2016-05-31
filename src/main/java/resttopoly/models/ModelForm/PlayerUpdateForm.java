package resttopoly.models.ModelForm;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class PlayerUpdateForm
{
    private String pawn;
    private String account;
    private boolean ready;

    public String getPawn()
    {
        return pawn;
    }

    public String getAccount()
    {
        return account;
    }
    
    public boolean isReady()
    {
        return ready;
    }
}
