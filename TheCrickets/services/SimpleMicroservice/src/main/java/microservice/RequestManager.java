package microservice;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager
{
    /**
     * sends a post http request
     * @param targetURL
     * @param object to send
     * @param typeReference type reference to the object to be received
     * @param <T> class for the received object
     * @return the received object
     */
    public static <T> T executePost(String targetURL, Object object, TypeReference<T> typeReference)
    {
        return executePost(targetURL, Json.serializer().toString(object), typeReference);
    }

    /**
     * sends a post http request
     * @param targetURL
     * @param urlParameters body of the post
     * @param typeReference type reference to the object to be received
     * @param <T> class for the received object
     * @return the received object
     */
    public static <T> T executePost(String targetURL, String urlParameters, TypeReference<T> typeReference)
    {
        HttpURLConnection connection = null;

        System.out.println(urlParameters);

        try
        {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            return Json.serializer().fromInputStream(connection.getInputStream(), typeReference);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }

    /**
     *
     * @param urlToRead
     * @param typeReference type reference to the object to be received
     * @param <T> class for the received object
     * @return the object received
     * @throws Exception if the GET fails
     */
    public static <T> T executeGet(String urlToRead, TypeReference<T> typeReference) throws Exception
    {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return Json.serializer().fromInputStream(conn.getInputStream(), typeReference);
    }
}
