import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

public class RoutingHandlers
{

    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        CRUD_operations crud_operations = new CRUD_operations();
        try
        {
            JsonUtilities.sendJson(exchange, crud_operations.readData(exchange.getQueryParameters().get("email").getFirst()));
        } catch (NumberFormatException e)
        {
            exchange.setStatusCode(400);
            JsonUtilities.sendJson(exchange, "Invalid number format");
        }

    }
}