package microservice;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager
{
    public static <T> T executePost(String targetURL, String urlParameters, TypeReference<T> typeReference)
    {
        HttpURLConnection connection = null;

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

    public static <T> T executeGet(String urlToRead, TypeReference<T> typeReference) throws Exception
    {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return Json.serializer().fromInputStream(conn.getInputStream(), typeReference);
    }
}
