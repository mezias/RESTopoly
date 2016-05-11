package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Components;
import resttopoly.models.Game;
import resttopoly.models.Player;
import resttopoly.models.Services;

import java.util.List;
@Data
public class GameResponse
{
    private static final String BASE_URL = "/games";

    private String id;

    private String name;

    private String players;

    private String components;

    private String services;

    private Game.GameStatus status;

    private GameResponse(){}

    public GameResponse(Game game){
        this.id = BASE_URL + "/"+game.getId();
        this.name = game.getName();
        this.players = BASE_URL+ "/"+game.getId()+"/players";
        this.components = BASE_URL+ "/"+game.getId()+"/components";
        this.services = BASE_URL+ "/"+game.getId()+"/services";
        this.status = game.getStatus();
    }
}
