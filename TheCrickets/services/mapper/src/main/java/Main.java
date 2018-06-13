public class Main {

    public static void main(final String[] args) {
        System.out.println("Starting the server...");
        Starter.startServices();
        Server.start();
    }
}