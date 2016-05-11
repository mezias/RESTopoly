package resttopoly.models.ModelForm;

import resttopoly.models.Services;

/**
 * @author GerritBode
 * @since 06/05/16
 */
public class GameForm
{
    private String name;
    
    private ServicesForm services;
    
    protected GameForm(){}

    public String getName()
    {
        return name;
    }

    public Services getServices()
    {
        return new Services(
                this.services.getGames(),
                this.services.getDice(),
                this.services.getBoards(),
                this.services.getBanks(),
                this.services.getBrokers(),
                this.services.getDecks(),
                this.services.getEvents()
        );
    }
}
