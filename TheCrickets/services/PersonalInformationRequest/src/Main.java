import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .post("api/login",new BlockingHandler(RoutingHandlers::userHandler))
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
