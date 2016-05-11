package resttopoly.models.repositories;

import resttopoly.models.Event;

import java.util.List;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public interface IEventRepository extends Repository
{
    List<Event> findAllEvents();
    Event findEvent(String id);
    Event createEvent(Event event) throws CannotCreateException;
    void deleteEvent(Event event);
}
