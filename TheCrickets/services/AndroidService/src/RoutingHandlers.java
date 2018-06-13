import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;


public class RoutingHandlers
{


    public static void notFoundHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.setStatusCode(404);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Page Not Found!!");
    }

    public static void appHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        System.out.println("u there?");
        exchange.setStatusCode(200);
        exchange.startBlocking();
        RequestData requestData = JsonUtilities.parseJson(exchange, new TypeReference<RequestData>()
        {
        });

        System.out.println(requestData.toString());
        StringBuilder message = new StringBuilder();

        CRUD_operations crudOperations = new CRUD_operations();
        ArrayList<Disaster> disasters = crudOperations.getAllEvents();
        Optional<Disaster> disaster = disasters.stream().filter(e ->
        {
            for (String s : requestData.getDisasters())
            {
                if (e.getType().equals(s))
                {
                    return true;
                }
            }
            return false;
        }).filter(e ->
        {
            long threeHours = 10800000;
            long tenAgo = System.currentTimeMillis() - 600000 + threeHours;
            return e.getTime().getTime() < tenAgo;
        }).filter(e ->
                DistanceCalculator.distance(requestData.getLatitude(), requestData.getLongitude(), e.getLatitude(), e.getLongitude()) < 50).findFirst();

        if (!disaster.isPresent())
        {
            exchange.setStatusCode(400);
            JsonUtilities.sendJson(exchange, "");
            return;
        }

        // here make the first part of the message
        message.append("Beware of: ").append(disaster.get().getType()).append("!");

        //here give the link where the user will be redirected as soon as he/she taps on the notification
        message.append("http://students.info.uaic.ro/~lucian.covaliu/");
        JsonUtilities.sendJson(exchange, message);

    }

    public static void addDisasterHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        exchange.startBlocking();
        Disaster requestData = JsonUtilities.parseJson(exchange, new TypeReference<Disaster>()
        {
        });

        CRUD_operations crudOperations = new CRUD_operations();

        requestData.setTime(Timestamp.from(Instant.now()));
        try
        {
            crudOperations.insertDisaster(requestData);
        } catch (Exception e)
        {
            exchange.setStatusCode(400);
            JsonUtilities.sendJson(exchange, "Something is not right!");
            return;
        }

        exchange.setStatusCode(200);
        JsonUtilities.sendJson(exchange, "Success!");


    }
}