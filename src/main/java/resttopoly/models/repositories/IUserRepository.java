package resttopoly.models.repositories;

import org.sql2o.Sql2oException;
import resttopoly.models.User;

import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 13/04/16
 */
public interface IUserRepository
{
    User findUser(String name);

    User createUser(User user) throws CannotCreateException;

    User updateUser(String oldName, User user) throws DatabaseConflictException;

    void deleteUser(User user)throws Sql2oException;

    List<User> findAllUsers();
}
