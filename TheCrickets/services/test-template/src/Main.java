import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/api/get/{email}", RoutingHandlers::testHandler)
            .get("/api/test", RoutingHandlers::helloHandler)
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        System.out.println("Starting server...");
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
