package resttopoly.models.responemodel;

import lombok.Data;

import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 10/04/16
 */
@Data
public class UsersResponse
{
    private List<String> users;

    public UsersResponse(List<String> userIds)
    {
        this.users = userIds;
    }
}
