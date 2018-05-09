import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

import java.util.Deque;

public class RoutingHandlers
{


    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void userHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        exchange.setStatusCode(200);
        User result = JsonUtilities.parseJson(exchange, new TypeReference<User>()
        {
        });
        System.out.println(result);

        if (result.email == null || result.firstName == null || result.lastName == null)
        {
            exchange.setStatusCode(400);
            JsonUtilities.sendJson(exchange, "Email or lastName or firstName incorrect!");
            return;
        }

        String mailRegex = result.email;
        String lastNameRegex = result.lastName;
        String firstNameRegex = result.firstName;

        if (mailRegex.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])") && lastNameRegex.matches("[a-zA-Z ]{2,30}") && firstNameRegex.matches("[a-zA-Z ]{2,30}"))
        {
            CRUD_operations operations = new CRUD_operations();
            User user = operations.readUserData(result.email);
            if (user.id == 0)
            {
                exchange.setStatusCode(401);
                JsonUtilities.sendJson(exchange, "User-ul nu exista!");
            }
            else
            {
                operations.updateUserData(user.id, result.firstName, result.lastName);

                exchange.setStatusCode(200);
                JsonUtilities.sendJson(exchange, "Date actualizate!");
            }

        }
        else
        {
            exchange.setStatusCode(401);
            JsonUtilities.sendJson(exchange, "Email or lastName or firstName incorrect!");
        }

    }
}