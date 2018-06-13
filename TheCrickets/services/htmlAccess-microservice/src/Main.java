import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/login", RoutingHandlers::testHandler)
            .get("/home", RoutingHandlersTwo::testHandler)
            .get("/profile", RoutingHandlersThree::testHandler)
            .get("/contact", RoutingHandlersFour::testHandler)
            .post("contact/submit",new BlockingHandler(RoutingHandlersFive::userHandler))
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        System.out.println("Starting server number 2...");
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
