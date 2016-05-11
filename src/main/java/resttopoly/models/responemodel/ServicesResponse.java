package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Services;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
@Data
public class ServicesResponse
{
    private String games;

    private String dice;

    private String boards;

    private String banks;

    private String brokers;

    private String decks;

    private String events;

    public ServicesResponse(Services services)
    {
        this.games = services.getGames();
        this.dice = services.getDice();
        this.boards = services.getBoards();
        this.banks = services.getBanks();
        this.brokers = services.getBrokers();
        this.decks = services.getDecks();
        this.events = services.getEvents();
    }
}
