import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.OriginHandler;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;
import microservice.ServerDetails;

public class RoutingHandlers
{


    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void mapHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseHeaders().put(Headers.ORIGIN, "*");
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        String name = exchange.getQueryParameters().get("name").getFirst();
        ServerDetails details = Server.map.getDetails(name);
        if (details == null)
        {
            exchange.setStatusCode(404);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Error, not found!");
        }
        else
        {
            JsonUtilities.sendJson(exchange, details);
        }
    }
}