package microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimpleServer
{
    private static final Logger logger = LoggerFactory.getLogger(SimpleServer.class);
    private static final int DEFAULT_PORT = 55555;

    private final Undertow.Builder undertowBuilder;

    private SimpleServer(Undertow.Builder undertow)
    {
        this.undertowBuilder = undertow;
    }

    /*
     * As you can see this class is not meant to abstract away the Undertow server,
     * its goal is simply to have some common configurations. We expose Undertow
     * if a different service needs to modify it in any way before we call start.
     */
    public Undertow.Builder getUndertow()
    {
        return undertowBuilder;
    }

    /**
     * starts the simple server and assigns a simple logger
     */
    public void start()
    {
        Undertow undertow = undertowBuilder.build();
        undertow.start();
        /*
         *  Undertow logs this on debug but we generally set 3rd party
         *  default logger levels to info so we log it here. If it wasn't using the
         *  io.undertow context we could turn on just that logger but no big deal.
         */
        undertow.getListenerInfo()
                .forEach(listenerInfo -> logger.info(listenerInfo.toString()));
    }

    /**
     * constructor
     * @param handler handlers for the server
     * @param port to start the server on
     * @return
     */
    public static SimpleServer simpleServer(HttpHandler handler, int port)
    {
        if (port <= 0)
            port = DEFAULT_PORT;
        Undertow.Builder undertow = null;
        try
        {
            System.out.println("Starting server on " + InetAddress.getLocalHost().getHostAddress() + ":" + port);

            undertow = Undertow.builder()
                    /*
                     * This setting is needed if you want to allow '=' as a value in a cookie.
                     * If you base64 encode any cookie values you probably want it on.
                     */
                    .setServerOption(UndertowOptions.ALLOW_EQUALS_IN_COOKIE_VALUE, true)
                    .addHttpListener(port, InetAddress.getLocalHost().getHostAddress(), handler);
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        return new SimpleServer(undertow);
    }
}