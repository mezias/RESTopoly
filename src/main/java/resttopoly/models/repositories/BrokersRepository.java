package resttopoly.models.repositories;

import resttopoly.models.Broker;
import resttopoly.models.repositories.CannotCreateException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class BrokersRepository implements IBrokersRepository, Repository {

    private Map<String, Broker> brokersMap;

    public BrokersRepository(Map<String, Broker> brokersMap) {
        this.brokersMap = brokersMap;
    }

    @Override
    public List<Broker> geAllBrokers() {

        List<Broker> brokerList = new ArrayList<>();

        for (Map.Entry<String, Broker> entry : this.brokersMap.entrySet()){
            brokerList.add(entry.getValue());
        }

        return brokerList;
    }

    @Override
    public Broker createBroker(Broker broker) throws CannotCreateException {
        if (brokersMap.containsKey(broker.getId())) {
            throw new CannotCreateException();
        }
        brokersMap.put(broker.getId(),broker);
        return broker;
    }

    @Override
    public Broker findBroker(String gameid)
    {
        return brokersMap.get(gameid);
    }
}
