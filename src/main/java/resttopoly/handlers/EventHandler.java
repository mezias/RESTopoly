package resttopoly.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import resttopoly.models.Event;
import resttopoly.models.ModelForm.EventForm;
import resttopoly.models.ModelForm.UserForm;
import resttopoly.models.User;
import resttopoly.models.repositories.CannotCreateException;
import resttopoly.models.repositories.IEventRepository;
import resttopoly.models.responemodel.EventResponse;
import resttopoly.models.responemodel.EventsResponse;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public class EventHandler
{

    private IEventRepository eventRepository;

    public EventHandler(IEventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    public EventsResponse findEvents(Request request, Response response)
    {

        List<Event> eventList = findEvents(request);
        List<String> eventIds = new ArrayList<>();

        for (Event event : eventList) {
            EventResponse eventResponse = new EventResponse(event.getId(),
                    event.getGame(),
                    event.getType(),
                    event.getName(),
                    event.getReason(),
                    event.getResource(),
                    event.getPlayer(),
                    event.getTime().toString());

            eventIds.add(eventResponse.getId());
        }

        response.status(HttpStatus.OK_200);

        return new EventsResponse(eventIds);
    }

    public String createEvent(Request request, Response response)
    {
        EventForm eventForm;

        try {
            eventForm = new ObjectMapper().readValue(request.body(), EventForm.class);
            Event event = new Event(eventForm.getId(), eventForm.getGame(), eventForm.getType(), eventForm.getName(), eventForm.getReason());
            event.setPlayer(eventForm.getPlayer());
            event.setResource(eventForm.getResource());
            try {
                eventRepository.createEvent(event);
                response.status(HttpStatus.CREATED_201);
            } catch (CannotCreateException e) {
                response.status(HttpStatus.CONFLICT_409);
                response.body("Event has already existed!");
            }
        } catch (Exception e) {
            response.status(HttpStatus.BAD_REQUEST_400);
        }
        return response.body();
    }

    public String deleteEvents(Request request, Response response)
    {
        List<Event> eventList = findEvents(request);
        for (Event event :
                eventList) {
            eventRepository.deleteEvent(event);
        }
        response.status(HttpStatus.OK_200);
        return "";
    }

    private List<Event> findEvents(Request request)
    {

        List<Event> notFilterEventList = eventRepository.findAllEvents();
        List<Event> eventList = null;

        eventList = filterEvent(notFilterEventList, FilterType.GAME, request.queryParams("game"));
        eventList = filterEvent(eventList, FilterType.TYPE, request.queryParams("type"));
        eventList = filterEvent(eventList, FilterType.REASON, request.queryParams("reason"));
        eventList = filterEvent(eventList, FilterType.RESOURCE, request.queryParams("resource"));
        eventList = filterEvent(eventList, FilterType.PLAYER, request.queryParams("player"));

        return eventList;
    }

    private List<Event> filterEvent(List<Event> toFilerEvents, FilterType FilterType, String filter)
    {

        List<Event> eventList = new ArrayList<>();
        String toBeMatched = "";

        if (filter != null) {
            if (!filter.equals("")) {
                Pattern pattern = Pattern.compile(filter);
                for (Event event :
                        toFilerEvents) {

                    switch (FilterType) {
                        case GAME:
                            toBeMatched = event.getGame();
                            break;
                        case TYPE:
                            toBeMatched = event.getType();
                            break;
                        case REASON:
                            toBeMatched = event.getReason();
                            break;
                        case RESOURCE:
                            toBeMatched = event.getResource();
                            break;
                        case PLAYER:
                            toBeMatched = event.getPlayer();
                            break;
                    }

                    if (pattern.matcher(toBeMatched).matches()) {
                        eventList.add(event);
                    }
                }
            } else {
                eventList = toFilerEvents;
            }
        } else {
            eventList = toFilerEvents;
        }


        return eventList;
    }

    public EventResponse findEvent(Request request, Response response, String eventId)
    {
        Event event = eventRepository.findEvent(eventId);
        EventResponse eventResponse = null;
        if (event != null) {
            eventResponse = new EventResponse(eventId, event.getGame(), event.getType(), event.getName(),
                    event.getReason(), event.getResource(), event.getPlayer(), event.getTime().toString());
            response.status(HttpStatus.OK_200);
        } else {
            response.status(HttpStatus.NOT_FOUND_404);
        }

        return eventResponse;
    }


    private enum FilterType
    {
        GAME, TYPE, REASON, RESOURCE, PLAYER
    }
}
