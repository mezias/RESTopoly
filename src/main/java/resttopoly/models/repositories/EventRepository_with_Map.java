package resttopoly.models.repositories;

import resttopoly.models.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public class EventRepository_with_Map implements IEventRepository
{
    private Map<String, Event> eventMap;

    public EventRepository_with_Map(Map<String, Event> eventMap)
    {
        this.eventMap = eventMap;
    }

    @Override
    public List<Event> findAllEvents()
    {
        List<Event> eventList = new ArrayList<>();

        for (Map.Entry<String,Event> e: eventMap.entrySet()) {
            eventList.add(e.getValue());
        }

        return eventList;
    }

    @Override
    public Event findEvent(String id)
    {
        return eventMap.get(id);
    }

    @Override
    public Event createEvent(Event event) throws CannotCreateException
    {
        if(eventMap.keySet().contains(event.getId()))
            throw new CannotCreateException();
        eventMap.put(event.getId(), event);

        return event;
    }

    @Override
    public void deleteEvent(Event event)
    {
        eventMap.remove(event.getId());
    }




}
