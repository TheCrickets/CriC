import io.undertow.server.HttpServerExchange;
import io.undertow.util.FileUtils;
import io.undertow.util.Headers;

import java.net.MalformedURLException;
import java.net.URL;


public class RoutingHandlersFive
{

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");

        String sessionID = exchange.getQueryParameters().get("sessionID").getFirst();
        String email = exchange.getQueryParameters().get("email").getFirst();

        System.out.println(sessionID + "- we now have sessionID (LOGOUT) and " + email);

        CRUD_operations operations = new CRUD_operations();

        URL url = null;

        try {

            operations.deleteSessionID(sessionID, String.valueOf(operations.readUserData(email).getId()));

            url = new URL("http://localhost:3000/static/logout/loggedOut.html");
            exchange.getResponseSender().send(FileUtils.readFile(url));
        } catch (MalformedURLException e) {
            System.out.println("URL problem!");
        }
    }
}