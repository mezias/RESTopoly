package resttopoly.models.responemodel;

import resttopoly.models.Broker;

public class EstatesResponse
{
    private String estates;

    public EstatesResponse(String estates)
    {
        this.estates = estates;
    }
    
    public EstatesResponse(Broker broker)
    {
        this.estates = broker.getEstates();
    }
}
