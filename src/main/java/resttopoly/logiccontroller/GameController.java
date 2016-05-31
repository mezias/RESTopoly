package resttopoly.logiccontroller;

import resttopoly.contexts.MutexContext;
import resttopoly.models.Components;
import resttopoly.models.Player;
import resttopoly.models.Services;
import resttopoly.models.repositories.*;

import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
public class GameController
{
    private final String gameId;
    private IPlayerRepository playerRepository;
    private IServiceRepository serviceRepository;
    private IComponentRepository componentRepository;
    private MutexContext mutexContext;
    private Player currentInTurnPlayer;

    public GameController(String gameId, Services services)
    {
        this.gameId = gameId;
        this.playerRepository = (IPlayerRepository) RepositoryProvider.getRepository(IPlayerRepository.class);
        this.playerRepository.createPlayerListForGame(gameId);
        this.serviceRepository = (IServiceRepository) RepositoryProvider.getRepository(IServiceRepository.class);
        this.serviceRepository.addServicesToGame(gameId, services);
        this.componentRepository = (IComponentRepository) RepositoryProvider.getRepository(IComponentRepository.class);
        this.componentRepository.createComponentsListForGame(gameId);
        this.mutexContext = new MutexContext();
    }


    public List<Player> getPlayers()
    {
        return playerRepository.findAllPlayers(gameId);
    }

    public Components getComponents()
    {
        return componentRepository.findComponents(gameId);
    }

    public Services getServices()
    {
        return serviceRepository.findServices(gameId);
    }

    public void updateServices(Services services)
    {
        Services servicesToUpdate = getServices();

        if (servicesToUpdate != null) {

            if (services.getBanks() != null) {
                servicesToUpdate.setBanks(services.getBanks());
            }

            if (services.getBoards() != null) {
                servicesToUpdate.setBoards(services.getBoards());
            }

            if (services.getBrokers() != null) {
                servicesToUpdate.setBrokers(services.getBrokers());
            }
            if (services.getDecks() != null) {
                servicesToUpdate.setDecks(services.getDecks());
            }
            if (services.getDice() != null) {
                servicesToUpdate.setDice(services.getDice());
            }
            if (services.getEvents() != null) {
                servicesToUpdate.setEvents(services.getEvents());
            }
            if (services.getGames() != null) {
                servicesToUpdate.setGames(services.getGames());
            }
        }
    }

    public void updateComponents(Components components)
    {
        Components componentsToUpdate = getComponents();

        if (componentsToUpdate != null) {

            if (components.getBank() != null) {
                componentsToUpdate.setBank(components.getBank());
            }

            if (components.getBoard() != null) {
                componentsToUpdate.setBoard(components.getBoard());
            }

            if (components.getBroker() != null) {
                componentsToUpdate.setBroker(components.getBroker());
            }
            if (components.getDecks() != null) {
                componentsToUpdate.setDecks(components.getDecks());
            }
            if (components.getDice() != null) {
                componentsToUpdate.setDice(components.getDice());
            }
            if (components.getEvents() != null) {
                componentsToUpdate.setEvents(components.getEvents());
            }
            if (components.getGame() != null) {
                componentsToUpdate.setGame(components.getGame());
            }
        }
    }

    public Player addPlayer(Player player) throws CannotCreateException
    {
        Player pl = playerRepository.findPlayer(gameId, player.getId());
        if (pl == null) {
            playerRepository.createPlayer(gameId, player);
            return player;
        } else {
            throw new CannotCreateException();
        }
    }


    public Player getPlayer(String id)
    {
        return playerRepository.findPlayer(gameId, id);
    }

    public void deletePlayer(Player player)
    {
        playerRepository.deletePlayer(gameId, player);
    }

    public Player updatePlayer(Player player)
    {
        Player playerToUpdate = getPlayer(player.getId());

        if (playerToUpdate != null) {

            if (player.getAccount() != null) {
                playerToUpdate.setAccount(player.getAccount());
            }

            if (player.getPawn() != null) {
                playerToUpdate.setPawn(player.getPawn());
            }

            playerToUpdate.setReady(player.getReady());

            return playerToUpdate;
        }
        return null;
    }

    public boolean aquireMutex(Player aquiringPlayer)
    {
        return mutexContext.acquire(aquiringPlayer);
    }

    public Player getCurrentInTurnPlayer()
    {
        return mutexContext.getCurrentPlayer();
    }

    public boolean releaseCurrentPlayer()
    {
        return this.mutexContext.release(this.mutexContext.getCurrentPlayer());
    }
}
