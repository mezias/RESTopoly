package resttopoly.models.repositories;

import resttopoly.models.Services;

import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class ServiceRepository implements IServiceRepository
{

    private Map<String, Services> servicesMap;

    public ServiceRepository(Map<String, Services> servicesMap)
    {
        this.servicesMap = servicesMap;
    }

    @Override
    public void addServicesToGame(String gameId, Services services)
    {
        servicesMap.put(gameId,services);
    }

    @Override
    public Services findServices(String gameId)
    {
        return servicesMap.get(gameId);
    }

    @Override
    public void updateServicesOfGame(String gameId, Services services)
    {
        servicesMap.put(gameId,services);
    }
}
