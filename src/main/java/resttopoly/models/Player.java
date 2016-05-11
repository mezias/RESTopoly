package resttopoly.models;

public class Player
{
    private String id;
    
    private String user;
    
    private String pawn;
    
    private String account;
    
    private boolean ready;

    public Player(String user, boolean ready) {
        this.user = user;
        String[] strarr = user.split("/");
        this.id = strarr[strarr.length-1];
        this.ready = ready;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPawn()
    {
        return pawn;
    }

    public void setPawn(String pawn)
    {
        this.pawn = pawn;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public boolean getReady()
    {
        return ready;
    }

    public void setReady(boolean ready)
    {
        this.ready = ready;
    }

    @Override
    public String toString()
    {
        return "Player [id=" + id + ", user=" + user + ", pawn=" + pawn
                + ", account=" + account + ", ready=" + ready + "]";
    }
}
