import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import microservice.Server;

import java.security.InvalidParameterException;

public class Main
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/login", RoutingHandlers::testHandler)
            .get("/home/{sessionID}/{email}", RoutingHandlersTwo::testHandler)
            .get("/home/{sessionID}/{email}/{checked}", RoutingHandlersTen::testHandler)
            .get("/profile/{sessionID}/{email}", RoutingHandlersThree::testHandler)
            .get("/contact", RoutingHandlersFour::testHandler)
            .get("/logout/{sessionID}/{email}", RoutingHandlersFive::testHandler)
            .get("/map/{sessionID}/{email}", RoutingHandlerOLD::testHandler)
            .post("/contact/submit",new BlockingHandler(RoutingHandlersSix::userHandler))
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        System.out.println("Starting server number 2...");
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
