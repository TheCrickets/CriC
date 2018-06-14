import io.undertow.server.HttpServerExchange;
import io.undertow.util.FileUtils;
import io.undertow.util.Headers;
import java.net.MalformedURLException;
import java.net.URL;


public class RoutingHandlersTwo
{

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");

        String sessionID = exchange.getQueryParameters().get("sessionID").getFirst();
        String email = exchange.getQueryParameters().get("email").getFirst();

        CRUD_operations operations = new CRUD_operations();


        if(operations.checkSessionExists(email)>2)
        {
            exchange.setStatusCode(400);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
            try {
                URL url = new URL("http://localhost:3000/static/loggedIn/loggedIn.html");
                exchange.getResponseSender().send(FileUtils.readFile(url));
            } catch (MalformedURLException e) {
                System.out.println("URL problem!");
            }

        }
        else {

            if(!operations.checkSessionIDValid(sessionID, email)) {
                exchange.setStatusCode(403);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Not authorized to enter this page!");
            }
            else {
                URL url = null;
                try {
                    url = new URL("http://localhost:3000/static/Home/Home.html");
                    exchange.getResponseSender().send(FileUtils.readFile(url));
                } catch (MalformedURLException e) {
                    System.out.println("URL problem!");
                }
            }
        }
    }
}