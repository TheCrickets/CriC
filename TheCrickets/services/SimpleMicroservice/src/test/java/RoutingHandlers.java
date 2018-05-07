import com.fasterxml.jackson.core.type.TypeReference;
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

    public static void postHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.setStatusCode(200);

        PostClass result = JsonUtilities.parseJson(exchange, new TypeReference<PostClass>(){});
        System.out.println(result);
        result.id++;
        // resend it with id+1
        JsonUtilities.sendJson(exchange, result);
    }
}