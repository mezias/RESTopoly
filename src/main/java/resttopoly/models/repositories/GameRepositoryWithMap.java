package resttopoly.models.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import resttopoly.models.Components;
import resttopoly.models.Game;
import resttopoly.models.Player;
import resttopoly.models.Services;
import resttopoly.models.User;

public class GameRepositoryWithMap implements IGameRepository //schauen ob die player ein eigenes repository
{
    private Map<String,Game> gamemap;
    
    private Map<String,Player> playermap;
    
    private Services service;
    
    private Components component;

    public GameRepositoryWithMap(Map<String, Game> gamemap, Map<String,Player> playermap)
    {
        this.gamemap = gamemap;
        this.playermap = playermap;
    }

    @Override
    public List<Game> findAllGames()
    {
        List<Game> gameList = new ArrayList<>();

        for (Map.Entry<String,Game> e: gamemap.entrySet()) {
            gameList.add(e.getValue());
        }
        
        return gameList;
    }

    @Override
    public Game createGame(Game game) throws CannotCreateException
    {
        try {
            gamemap.put(game.getName(),game);
        }catch (Exception e){
            throw new CannotCreateException();
        }

        return game;
    }

    @Override
    public Game findGame(String name)
    {
        return gamemap.get("name");
    }

    @Override
    public String getStatus(String name)
    {
        return gamemap.get("name").getStatus();
    }

    @Override
    public String setStatus(String status) //prüft alle spieler und wenn alle ready dann running, ende noch nicht
    {
//        for (Map.Entry<String,Player> e: playermap.entrySet()) {
//            if(e.getValue().getReady() != "ready")
//            {
//                return "nicht Ready";
//            }
//        }        
//        return "ready";
        
        return "";
    }
    
    @Override
    public Services getService()
    {
        return service;
    }
    
    @Override
    public void setService(String service)
    {
        switch(service)
        {
            case "game": this.service.setGame(service);
            break;
            case "dice": this.service.setDice(service);
            break;
            case "board": this.service.setBoard(service);
            break;
            case "bank": this.service.setBank(service);
            break;
            case "broker": this.service.setBroker(service);
            break;
            case "decks": this.service.setDecks(service);
            break;
            case "events": this.service.setEvents(service);
            break;
        }
    }

    @Override
    public Components getComponents()
    {
        return component;
    }
    

//    @Override
//    public void setService(Services service)
//    {
//        gamemap.get("name").setServices(Services service);
//    }
    

    @Override
    public void setComponent(String component)
    {
        switch(component)
        {
            case "game": this.component.setGame(component);
            break;
            case "dice": this.component.setDice(component);
            break;
            case "board": this.component.setBoard(component);
            break;
            case "bank": this.component.setBank(component);
            break;
            case "broker": this.component.setBroker(component);
            break;
            case "decks": this.component.setDecks(component);
            break;
            case "events": this.component.setEvents(component);
            break;
        }
        
    }

    @Override
    public List<String> findAllPlayers()
    {
        List<String> playerList = new ArrayList<>();

        for (Map.Entry<String,Player> e: playermap.entrySet()) {
            playerList.add(e.getValue().getId());
        }
        
        return playerList;
    }

    @Override
    public Player createNewPlayer(String id, String user, String pawn,
            String account, String ready)
    {
        Player player = new Player(id, user, pawn, account, ready);
        return player;
    }

    @Override
    public Player findPlayer(String id)
    {
        Player searchedPlayer = null;
        for (Map.Entry<String,Player> e: playermap.entrySet()) {
            if(e.getKey() == id)
            {
                searchedPlayer = e.getValue();
            }
        }
        return searchedPlayer;
    }

    @Override
    public void putPlayer(Player player)
    {
        playermap.put(player.getId(), player);        
    }

    @Override
    public void deletePlayer(String id)
    {
        for (Map.Entry<String,Player> e: playermap.entrySet()) {
            if(e.getKey() == id)
            {
                playermap.remove(id);
            }
        }
    }

    @Override
    public String getReady()
    {
        return playermap.get("id").getReady(); 
    }
    
    @Override
    public String setPlayerReady(String id)
    {
        for (Map.Entry<String,Player> e: playermap.entrySet()) {
            if(e.getKey() == id)
            {
                e.getValue().setReady("ready");
                return "player: " + id + " ready";
            }
        }
        return "";
    }

    @Override
    public Player getCurrentPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPlayersTurn()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized String tryTakeTurn()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void endTurn()
    {
        // TODO Auto-generated method stub

    }    
}