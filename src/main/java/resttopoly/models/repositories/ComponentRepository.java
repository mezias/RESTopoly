package resttopoly.models.repositories;

import resttopoly.models.Components;

import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class ComponentRepository implements IComponentRepository
{
    private Map<String, Components> componentsMap;

    public ComponentRepository(Map<String, Components> componentsMap)
    {
        this.componentsMap = componentsMap;
    }

    @Override
    public void createComponentsListForGame(String gameId)
    {
        componentsMap.put(gameId, new Components());
    }

    @Override
    public Components findComponents(String gameId)
    {
        return componentsMap.get(gameId);
    }
}
