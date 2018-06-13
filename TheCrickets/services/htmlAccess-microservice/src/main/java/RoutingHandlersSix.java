import com.fasterxml.jackson.core.type.TypeReference;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import microservice.JsonUtilities;

public class RoutingHandlersSix {

    public static String SENDTO = "luciancovaliu41@gmail.com ";

    public static void userHandler(HttpServerExchange exchange)
    {


        exchange.getResponseHeaders().put(HttpString.tryFromString("Access-Control-Allow-Origin"), "*");

        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

        exchange.setStatusCode(200);

        Contact result = JsonUtilities.parseJson(exchange, new TypeReference<Contact>() {});

        Email.doSendMail("theCrikets2@gmail.com", "!@#$%678", SENDTO,"[The Crickets - comments]",
                "Dear Administrator,\n" +
                        "\n" +
                        "Mr/Mrs " + result.getFirstName() + " " + result.getLastName() + " sent you a comment.\n" +
                        "Below you can read his comment:\n" +
                        "\n" +
                        "  \"" + result.getComment() + "\"\n" +
                        "\n" +
                        "\n" +
                        "You can contact him/her using the email adress: " + result.getEmail() + "\n" +
                        "\n" +
                        "This email was automatically sent by htmlAccess-Microservice\n" +
                        "Have a wonderful day!"
        );

    }
}
