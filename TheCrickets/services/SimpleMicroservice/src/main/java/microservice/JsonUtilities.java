package microservice;

import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


import java.nio.ByteBuffer;

public class JsonUtilities
{
    /**
     * Sends the object to the exchange
     * @param exchange to get the sender from
     * @param obj to send
     */
    public static void sendJson(HttpServerExchange exchange, Object obj)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(ByteBuffer.wrap(Json.serializer().toByteArray(obj)));
    }

    /**
     *
     * @param exchange to get the input from
     * @param typeRef type reference to the class that will be populated
     * @param <T> the class to be populated
     * @return the object parsed from the input stream
     */

    public static <T> T parseJson(HttpServerExchange exchange, TypeReference<T> typeRef)
    {
        return Json.serializer().fromInputStream(exchange.getInputStream(), typeRef);
    }
}
