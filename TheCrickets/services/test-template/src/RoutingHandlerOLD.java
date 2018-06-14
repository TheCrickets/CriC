import io.undertow.server.HttpServerExchange;
import io.undertow.util.FileUtils;
import io.undertow.util.Headers;

import java.net.MalformedURLException;
import java.net.URL;


public class RoutingHandlerOLD
{

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");

        String sessionID = exchange.getQueryParameters().get("sessionID").getFirst();
        String email = exchange.getQueryParameters().get("email").getFirst();

        CRUD_operations operations = new CRUD_operations();

        URL url = null;

        try {

            if(operations.checkSessionExists(email) != 0) {
                exchange.setStatusCode(200);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                url = new URL("http://localhost:3000/static/map/map.html");
                exchange.getResponseSender().send(FileUtils.readFile(url));
            }

            else {
                exchange.setStatusCode(400);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Not authorized to enter this page!");
            }
        } catch (MalformedURLException e) {
            System.out.println("URL problem!");
        }
    }
}
