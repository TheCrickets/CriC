import io.undertow.server.HttpServerExchange;
import io.undertow.util.FileUtils;
import io.undertow.util.Headers;

import java.net.MalformedURLException;
import java.net.URL;


public class RoutingHandlersThree
{

    public static void testHandler(HttpServerExchange exchange)
    {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        URL url = null;
        try {
            url = new URL("http://localhost:3000/static/home/PersonalInformationFrom.html");
            exchange.getResponseSender().send(FileUtils.readFile(url));
        } catch (MalformedURLException e) {
            System.out.println("URL problem!");
        }
    }
}