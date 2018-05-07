import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.BlockingHandler;
import microservice.Json;
import microservice.RequestManager;
import microservice.Server;

import java.security.InvalidParameterException;

class test
{
    String name;
    String job;

    public void setJob(String job)
    {
        this.job = job;
    }

    public String getName()
    {
        return name;
    }

    public String getJob()
    {
        return job;
    }

    public void setName(String name)

    {

        this.name = name;
    }

    public test()
    {
    }

    public test(String name, String job)
    {
        this.name = name;
        this.job = job;
    }
}

class test2
{
    int id;
    String createdAt;

    public void setId(int id)
    {
        this.id = id;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }


    @Override
    public String toString()
    {
        return "test2{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    test2()
    {
    }
}

public class TestServer
{
    private static final HttpHandler ROUTES = new RoutingHandler()
            .get("/api/test/{n1}/{n2}", RoutingHandlers::testHandler)
            //.post("api/post",RoutingHandlers::postHandler)
            .post("api/post",new BlockingHandler(RoutingHandlers::postHandler))
            .setFallbackHandler(RoutingHandlers::notFoundHandler);

    public static void main(String[] args)
    {
        System.out.println(RequestManager.executePost("https://reqres.in/api/users", new test("morpheus", "leader"), new TypeReference<test2>()
        {
        }));
        // face un request cu post la un server de undeva de pe net, trimite un obiect si primeste altul de tipul din TypeReference


        if (args.length < 1)
            throw new InvalidParameterException();
        Server.start(Integer.parseInt(args[0]), ROUTES);
    }
}
