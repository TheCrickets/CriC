import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .post("/api/notification", new BlockingHandler(RoutingHandlers::testHandler))
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        System.out.println("Starting server number 2...");
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
