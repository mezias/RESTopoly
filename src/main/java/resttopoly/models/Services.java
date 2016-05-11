package resttopoly.models;

public class Services
{
    private String games;
    
    private String dice;
    
    private String boards;
    
    private String banks;
    
    private String brokers;
    
    private String decks;
    
    private String events;

    public Services(String game, String dice, String board, String bank,
            String broker, String decks, String events) {
        this.games = game;
        this.dice = dice;
        this.boards = board;
        this.banks = bank;
        this.brokers = broker;
        this.decks = decks;
        this.events = events;
    }

    public String getGames()
    {
        return games;
    }

    public void setGames(String games)
    {
        this.games = games;
    }

    public String getDice()
    {
        return dice;
    }

    public void setDice(String dice)
    {
        this.dice = dice;
    }

    public String getBoards()
    {
        return boards;
    }

    public void setBoards(String boards)
    {
        this.boards = boards;
    }

    public String getBanks()
    {
        return banks;
    }

    public void setBanks(String banks)
    {
        this.banks = banks;
    }

    public String getBrokers()
    {
        return brokers;
    }

    public void setBrokers(String brokers)
    {
        this.brokers = brokers;
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
        return "Services [games=" + games + ", dice=" + dice + ", boards=" + boards
                + ", banks=" + banks + ", brokers=" + brokers + ", decks=" + decks
                + ", events=" + events + "]";
    }
}
