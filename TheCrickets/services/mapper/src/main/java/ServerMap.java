import microservice.ServerDetails;

import java.util.HashMap;
import java.util.Map;

public class ServerMap
{
    private static Map<String, ServerDetails> map = new HashMap<>();
    private static ServerMap instance = new ServerMap();

    private ServerMap()
    {
    }

    public ServerDetails getDetails(String name)
    {
        return map.get(name);

    }

    public void addDetails(String name, ServerDetails serverDetails)
    {
        if (map.put(name, serverDetails) != null)
        {
            System.out.println("Replaced");
        }
    }

    public void deleteDetails(String name)
    {
        map.remove(name);
    }

    public void clear()
    {
        map.clear();
    }

    public static ServerMap getInstance()
    {
        return instance;
    }
}
