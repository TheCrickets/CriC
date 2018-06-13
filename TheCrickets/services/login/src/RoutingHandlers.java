import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

import java.util.Deque;

public class RoutingHandlers
{

    /**
     *  Checking if the page exist
     */
    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }
    /**
     * Checking if the email and the password is correct
     */
    public static void userHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        exchange.setStatusCode(200);
        User result = JsonUtilities.parseJson(exchange, new TypeReference<User>()
        {
        });
        System.out.println(result);

        //String mailRegex = exchange.getQueryParameters().get("email").getFirst();

        String mailRegex = result.email;
        /**
         * Checking if the email is correct
         */
        if (mailRegex.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
        {
            CRUD_operations operations = new CRUD_operations();
            /**
             * Checking if the email and the password exist
             */
            if (operations.checkUserExists(result.email, result.password))
            {
                exchange.setStatusCode(200);
                String sessionID = operations.addSessionForUser(operations.readUserData(result.email).id);
                JsonUtilities.sendJson(exchange, sessionID);
            }
            else
            {
                exchange.setStatusCode(401);
                JsonUtilities.sendJson(exchange, "Ati gresit email-ul sau parola!");
            }
/*
            exchange.setStatusCode(200);
            JsonUtilities.sendJson(exchange, "Email si parola corecta!");
            */
        }

        else
        {
            exchange.setStatusCode(401);
            JsonUtilities.sendJson(exchange, "Email incorrect!");
        }

    }
}