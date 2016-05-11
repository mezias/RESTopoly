package resttopoly.models;

public class Components
{
    private String game;
    
    private String dice;
    
    private String board;
    
    private String bank;
    
    private String broker;
    
    private String decks;
    
    private String events;

    public Components(String game, String dice, String board, String bank,
            String broker, String decks, String events) {
        super();
        this.game = game;
        this.dice = dice;
        this.board = board;
        this.bank = bank;
        this.broker = broker;
        this.decks = decks;
        this.events = events;
    }

    public String getGame()
    {
        return game;
    }

    public void setGame(String game)
    {
        this.game = game;
    }

    public String getDice()
    {
        return dice;
    }

    public void setDice(String dice)
    {
        this.dice = dice;
    }

    public String getBoard()
    {
        return board;
    }

    public void setBoard(String board)
    {
        this.board = board;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public String getBroker()
    {
        return broker;
    }

    public void setBroker(String broker)
    {
        this.broker = broker;
    }

    public String getDecks()
    {
        return decks;
    }

    public void setDecks(String decks)
    {
        this.decks = decks;
    }

    public String getEvents()
    {
        return events;
    }

    public void setEvents(String events)
    {
        this.events = events;
    }
    
    @Override
    public String toString()
    {
        return "Components [game=" + game + ", dice=" + dice + ", board="
                + board + ", bank=" + bank + ", broker=" + broker + ", decks="
                + decks + ", events=" + events + "]";
    }    
}
