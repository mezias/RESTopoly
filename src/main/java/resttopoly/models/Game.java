package resttopoly.models;

import lombok.Data;
import resttopoly.logiccontroller.GameController;
import resttopoly.models.ModelForm.ServicesForm;
import resttopoly.models.repositories.CannotCreateException;

import java.util.List;

/**
 * @author GerritBode
 * @since 06/05/16
 */
@Data
public class Game
{
    private String id;
    
    private String name;
    
    private List<Player> players;
    
    private Components components;
    
    private Services services;
    
    private GameStatus status;

    private GameController gameController;

    public Game(String name, Services services)
    {
        this.name = name;
        this.services = services;
        this.status = GameStatus.Registration;
    }

    public void setGameController(GameController gameController)
    {
        this.gameController = gameController;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }

    public List<Player> getPlayers()
    {
        return gameController.getPlayers();
    }

    public Components getComponents()
    {
        return gameController.getComponents();
    }

    public Services getServices()
    {
        return gameController.getServices();
    }

    public GameStatus getStatus()
    {
        return status;
    }

    public GameController getGameController()
    {
        return gameController;
    }

    public void updateServices(Services services)
    {
        gameController.updateServices(services);
    }

    public void updateComponents(Components components)
    {
        gameController.updateComponents(components);
    }

    public Player addPlayerToGame(Player player) throws CannotCreateException
    {
        return gameController.addPlayer(player);
    }

    public Player getPlayer(String id)
    {
        return gameController.getPlayer(id);
    }

    public void deletePlayer(Player player)
    {
        gameController.deletePlayer(player);
    }

    public Player updatePlayer(Player player)
    {
        return gameController.updatePlayer(player);
    }

    public boolean acquireMutex(Player aquiringPlayer)
    {
        return gameController.aquireMutex(aquiringPlayer);
    }

    public Player getCurrentInTurnPlayer()
    {
        return gameController.getCurrentInTurnPlayer();
    }

    public boolean releaseMutex()
    {
        return gameController.releaseCurrentPlayer();
    }

    public enum GameStatus{
        Registration, Running, Finish
    }
}
