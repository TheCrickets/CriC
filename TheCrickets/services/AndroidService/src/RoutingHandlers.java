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
        System.out.println("u there?");
        exchange.setStatusCode(200);
        exchange.startBlocking();
        RequestData requestData = JsonUtilities.parseJson(exchange, new TypeReference<RequestData>(){});

        System.out.println(requestData.toString());
        JsonUtilities.sendJson(exchange, "Hello world!");

    }
}