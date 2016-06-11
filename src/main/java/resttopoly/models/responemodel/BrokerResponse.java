package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Broker;

/**
 * Created by minhnguy on 01.06.2016.
 */
@Data
public class BrokerResponse {

    private static final String BASE_PATH = "/brokers";

    private String id;

    private String game;

    private String estates;

    public BrokerResponse(String id, String game, String estates) {
        this.id = id;
        this.game = game;
        this.estates = estates;
    }

    public BrokerResponse(Broker broker)
    {
        this.id = broker.getId();
        this.game = broker.getGame();
        this.estates = broker.getEstates();
    }
}
