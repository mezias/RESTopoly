package resttopoly.models.ModelForm;

import resttopoly.models.Services;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class ServicesForm
{
    private String games;

    private String dice;

    private String boards;

    private String banks;

    private String brokers;

    private String decks;

    private String events;

    public Services getServices()
    {
        return new Services(
                this.getGames(),
                this.getDice(),
                this.getBoards(),
                this.getBanks(),
                this.getBrokers(),
                this.getDecks(),
                this.getEvents()
        );
    }

    public String getGames()
    {
        return games;
    }

    public String getDice()
    {
        return dice;
    }

    public String getBoards()
    {
        return boards;
    }

    public String getBanks()
    {
        return banks;
    }

    public String getBrokers()
    {
        return brokers;
    }

    public String getDecks()
    {
        return decks;
    }

    public String getEvents()
    {
        return events;
    }
}
