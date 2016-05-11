package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Components;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
@Data
public class ComponentsResponse
{
    private String game;

    private String dice;

    private String board;

    private String bank;

    private String broker;

    private String decks;

    private String events;

    public ComponentsResponse(Components components)
    {
        this.game = components.getGame();
        this.dice = components.getDice();
        this.board = components.getBoard();
        this.bank = components.getBank();
        this.broker = components.getBroker();
        this.decks = components.getDecks();
        this.events = components.getEvents();
    }
}
