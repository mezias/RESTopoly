package resttopoly.models.responemodel;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class GamesResponse
{
    private static final String GAME_BASE_PATH = "/games";

    private List<String> games;
    
    private GamesResponse(){}

    public GamesResponse(List<String> gameIds)
    {

        this.games = getResponseIds(gameIds);

    }

    private List<String> getResponseIds(List<String> gameIds)
    {
        List<String> responseIds = new ArrayList<>();
        for (String id :gameIds
                ) {
            responseIds.add(GAME_BASE_PATH+"/"+id);
        }

        return responseIds;
    }
}
