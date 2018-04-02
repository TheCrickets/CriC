import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Server {
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/api/map/{name}", RoutingHandlers::mapHandler)
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static ServerMap map = ServerMap.getInstance();

    public static void start() {

        //map.addDetails("test1",new ServerDetails(55555, "localhost"));
        map.addDetails("mapper",new ServerDetails("localhost",55555));

        Path path = Paths.get("config.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i+=2) {
                String[] details = lines.get(i + 1).split(":");
                map.addDetails(lines.get(i),new ServerDetails(details[0],Integer.parseInt(details[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleServer server = SimpleServer.simpleServer(ROUTES, -1);
        server.start();
    }
}
