package resttopoly.models.ModelForm;

import resttopoly.models.Components;
import resttopoly.models.Services;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class ComponentsForm
{
    private String game;

    private String dice;

    private String board;

    private String bank;

    private String broker;

    private String decks;

    private String events;

    private ComponentsForm()
    {
    }

    public Components getComponents()
    {
        return new Components(
                this.getGame(),
                this.getDice(),
                this.getBoard(),
                this.getBank(),
                this.getBroker(),
                this.getDecks(),
                this.getEvents()
        );
    }

    public String getGame()
    {
        return game;
    }

    public String getDice()
    {
        return dice;
    }

    public String getBoard()
    {
        return board;
    }

    public String getBank()
    {
        return bank;
    }

    public String getBroker()
    {
        return broker;
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
