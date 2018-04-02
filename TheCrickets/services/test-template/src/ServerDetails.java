public class ServerDetails
{
    private int port;
    private String url;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public ServerDetails(String url, int port)
    {
        this.port = port;
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ServerDetails{" +
                "port=" + port +
                ", url='" + url + '\'' +
                '}';
    }

    public ServerDetails()
    {

    }
}
