package resttopoly.models.responemodel;

import java.util.List;

public class GamesResponse
{
    private List<String> games;

    public GamesResponse(List<String> eventIds)
    {
        this.games = eventIds;
    }
}
