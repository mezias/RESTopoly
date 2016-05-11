package resttopoly.models.repositories;

import resttopoly.models.Services;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public interface IServiceRepository extends Repository
{
    void addServicesToGame(String gameId, Services services);

    Services findServices(String gameId);


    void updateServicesOfGame(String gameId, Services services);
}
