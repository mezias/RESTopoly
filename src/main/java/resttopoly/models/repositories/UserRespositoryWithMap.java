package resttopoly.models.repositories;

import org.sql2o.Sql2oException;
import resttopoly.models.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public class UserRespositoryWithMap implements IUserRepository
{
    private Map<String,User> usermap;

    public UserRespositoryWithMap(Map<String, User> usermap)
    {
        this.usermap = usermap;
    }

    @Override
    public User findUser(String name)
    {
        return usermap.get("name");
    }

    @Override
    public User createUser(User user) throws CannotCreateException
    {
        try {
            usermap.put(user.getName(),user);
        }catch (Exception e){
            throw new CannotCreateException();
        }

        return user;
    }

    @Override
    public User updateUser(String oldName, User user) throws DatabaseConflictException
    {
        usermap.get(oldName).setUri(user.getUri());
        usermap.get(oldName).setName(user.getName());
        return user;
    }

    @Override
    public void deleteUser(User user) throws Sql2oException
    {
        usermap.remove(user.getName());
    }

    @Override
    public List<User> findAllUsers()
    {
        
        List<User> userList = new ArrayList<>();

        for (Map.Entry<String,User> e: usermap.entrySet()) {
            userList.add(e.getValue());
        }
        
        return userList;
    }
}
