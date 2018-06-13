import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

public class RoutingHandlersFive {

    public static void userHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        exchange.setStatusCode(200);

        Contact result = JsonUtilities.parseJson(exchange, new TypeReference<>() {});

        System.out.println(result);

    }
}
