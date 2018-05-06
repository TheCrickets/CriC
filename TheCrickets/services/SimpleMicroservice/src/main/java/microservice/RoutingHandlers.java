package microservice;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class RoutingHandlers
{


    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        String n1 = exchange.getQueryParameters().get("n1").getFirst();
        String n2 = exchange.getQueryParameters().get("n2").getFirst();
        try
        {
            int rez = Integer.parseInt(n1) + Integer.parseInt(n2);
            JsonUtilities.sendJson(exchange, rez);
        } catch (NumberFormatException e)
        {
            exchange.setStatusCode(400);
            JsonUtilities.sendJson(exchange, "Invalid number format");
        }

    }
}