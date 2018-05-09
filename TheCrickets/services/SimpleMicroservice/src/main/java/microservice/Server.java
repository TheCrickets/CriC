package microservice;

import io.undertow.server.HttpHandler;

public class Server
{
    /**
     * starts a simple server
     * @param port to run the server on
     * @param ROUTES handlers (HttpHandler)
     */
    public static void start(int port, HttpHandler ROUTES)
    {
        SimpleServer server = SimpleServer.simpleServer(ROUTES, port);
        server.start();
    }
}
