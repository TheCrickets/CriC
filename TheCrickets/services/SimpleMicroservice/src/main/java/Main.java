import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import microservice.RoutingHandlers;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{

    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/api/test/{n1}/{n2}", RoutingHandlers::testHandler)
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        Server.start(Integer.parseInt(args[0]),ROUTES);
    }
}
