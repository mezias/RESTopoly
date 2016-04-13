package resttopoly.models.responemodel;

import lombok.Data;
import resttopoly.models.Event;

import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
@Data
public class EventsResponse
{
    private List<String> events;

    public EventsResponse(List<String> eventIds)
    {
        this.events = eventIds;
    }
}
