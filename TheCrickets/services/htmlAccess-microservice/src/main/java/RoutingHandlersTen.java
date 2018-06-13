import io.undertow.server.HttpServerExchange;
import io.undertow.util.FileUtils;
import io.undertow.util.Headers;
import java.net.MalformedURLException;
import java.net.URL;


public class RoutingHandlersTen
{

    public static void testHandler(HttpServerExchange exchange)
    {

                URL url = null;
                try {
                    url = new URL("http://localhost:3000/static/Home/Home.html");
                    exchange.getResponseSender().send(FileUtils.readFile(url));
                } catch (MalformedURLException e) {
                    System.out.println("URL problem!");
                }
            }
        }