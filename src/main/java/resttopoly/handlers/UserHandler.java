package resttopoly.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2oException;
import resttopoly.models.ModelForm.UserForm;
import resttopoly.models.User;
import resttopoly.models.repositories.CannotCreateException;
import resttopoly.models.repositories.DatabaseConflictException;
import resttopoly.models.repositories.IUserRepository;
import resttopoly.models.responemodel.UserResponse;
import resttopoly.models.responemodel.UsersResponse;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class UserHandler
{

    private IUserRepository userRepository;

    private static final String BASE_PATH = "/users";

    public UserHandler(IUserRepository IUserRepository)
    {
        this.userRepository = IUserRepository;
    }

    /**
     * @param name
     * @param request
     * @param response
     * @return
     */
    public UserResponse findUser(String name, Request request, Response response)
    {
        User user = userRepository.findUser(name);


        if (user == null) {
            response.status(HttpStatus.NOT_FOUND_404);
            return null;
        } else {
            UserResponse userResponse = new UserResponse("some id", user.getName(), user.getUri());
            response.status(HttpStatus.OK_200);
            return userResponse;
        }
    }


    public String saveOrUpdate(String name, Request request, Response response)
    {
        String toUpdatename = request.queryParams("name");
        String uri = request.queryParams("uri");
        User user = userRepository.findUser(name);

        if (user == null) {
            user = new User(toUpdatename, uri);
            try {
                user = userRepository.createUser(user);
                response.status(HttpStatus.CREATED_201);
            } catch (CannotCreateException e) {
                response.status(HttpStatus.CONFLICT_409);
            }
        } else {
            user.setName(toUpdatename);
            user.setUri(uri);
            try {
                userRepository.updateUser(name, user);
                response.status(HttpStatus.OK_200);
            } catch (DatabaseConflictException e) {
                response.status(HttpStatus.CONFLICT_409);
            }
        }

        return "";
    }

    public String deleteUser(String username, Request request, Response response)
    {
        User user = userRepository.findUser(username);

        if (user == null) {
            response.status(HttpStatus.NOT_FOUND_404);
        } else {
            try {
                userRepository.deleteUser(user);
                response.status(HttpStatus.OK_200);
            } catch (Sql2oException e) {
                response.status(HttpStatus.NOT_FOUND_404);
            }
        }

        return "";
    }

    public UsersResponse getAllUsers(Request request, Response response)
    {
        List<User> users = userRepository.findAllUsers();
        List<String> userIds = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse(BASE_PATH, user.getName(), user.getUri());
            userIds.add(userResponse.getId());
        }

        response.status(HttpStatus.OK_200);

        return new UsersResponse(userIds);
    }

    public String createUser(Request request, Response response) throws IOException
    {

        UserForm userForm;
        try {

            userForm = new ObjectMapper().readValue(request.body(), UserForm.class);
            User user = new User(userForm.getName(), userForm.getUri());
            try {
                userRepository.createUser(user);
                response.status(HttpStatus.CREATED_201);
            } catch (CannotCreateException e) {
                response.status(HttpStatus.CONFLICT_409);
            }
        } catch (JsonMappingException | JsonParseException e) {
            response.status(HttpStatus.BAD_REQUEST_400);
        }

        return "";
    }
}
