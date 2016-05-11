package resttopoly.models.responemodel;

import lombok.Data;

/**
 * @author DucNguyenMinh
 * @since 11/05/16
 */
@Data
public class StatusResponse
{
    private String status;

    public StatusResponse(String status)
    {
        this.status = status;
    }
}
