import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Server
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/api/test/{n1}/{n2}", RoutingHandlers::testHandler)
            .setFallbackHandler(RoutingHandlers::notFoundHandler);


    public static void start(int port)
    {
        SimpleServer server = SimpleServer.simpleServer(ROUTES, port);
        server.start();

        /*
        // test for Get
        try
        {
            System.out.println(RequestManager.executeGet("http://localhost:55555/api/map/mapper", new TypeReference<ServerDetails>()
            {
            }));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //test for post

        System.out.println(RequestManager.executePost("https://slack.com/api/api.test", "", new TypeReference<ServerDetails>()
        {
        }));
        */
    }
}
