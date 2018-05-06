import java.security.InvalidParameterException;

public class Main
{

    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new InvalidParameterException();
        Server.start(Integer.parseInt(args[0]));
    }
}
