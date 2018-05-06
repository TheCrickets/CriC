package microservice;

import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


import java.nio.ByteBuffer;

public class JsonUtilities
{

    static void sendJson(HttpServerExchange exchange, Object obj)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(ByteBuffer.wrap(Json.serializer().toByteArray(obj)));
    }

    static <T> T parseJson(HttpServerExchange exchange, TypeReference<T> typeRef)
    {
        return Json.serializer().fromInputStream(exchange.getInputStream(), typeRef);
    }
}
