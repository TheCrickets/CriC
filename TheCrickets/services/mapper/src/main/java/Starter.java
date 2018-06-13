import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Starter
{
    private static final File folder = new File("services");
    private static final List<File> files = new ArrayList<>();
    private static int port = 8100;


    static private void discoverFiles()
    {
        for (File file : folder.listFiles())
        {
            if (file.getName().endsWith(".jar"))
                files.add(file);
        }
    }

    static public void startServices()
    {
        discoverFiles();
        files.forEach(e -> Executors.newCachedThreadPool().execute(() -> {
            try
            {
                System.out.println(e.getAbsolutePath());

                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "\"" + e.getAbsolutePath() + "\"", String.valueOf(port));

                Process process = pb.start();

                Scanner s = new Scanner(process.getErrorStream()).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                System.out.println(result);

            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }));
    }
}
