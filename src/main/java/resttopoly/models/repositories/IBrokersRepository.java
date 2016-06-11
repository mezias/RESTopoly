package resttopoly.models.repositories;

import resttopoly.models.Broker;
import resttopoly.models.repositories.CannotCreateException;

import java.util.List;

import resttopoly.models.ModelForm.BrokerForm;

/**
 * Created by minhnguy on 01.06.2016.
 */
public interface IBrokersRepository extends IRepository {
    List<Broker> geAllBrokers();

    Broker createBroker(Broker broker) throws CannotCreateException;

    Broker findBroker(String gameid);

}
