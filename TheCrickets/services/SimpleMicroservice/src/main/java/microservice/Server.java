package microservice;

import io.undertow.server.HttpHandler;

public class Server
{
    public static void start(int port, HttpHandler ROUTES)
    {
        SimpleServer server = SimpleServer.simpleServer(ROUTES, port);
        server.start();
    }
}
