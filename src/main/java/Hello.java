
import static spark.Spark.get;

/**
 * @author DucNguyenMinh
 * @since 09/04/16
 */
public class Hello
{

    public static void main(String args[]){


        get("/", ((request, response) -> "Hello from Minh Duc Nguyen"));

    }
}
