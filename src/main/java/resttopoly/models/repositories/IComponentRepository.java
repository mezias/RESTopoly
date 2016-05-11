package resttopoly.models.repositories;

import resttopoly.models.Components;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public interface IComponentRepository extends Repository
{
    void createComponentsListForGame(String gameId);

    Components findComponents(String gameId);
}
