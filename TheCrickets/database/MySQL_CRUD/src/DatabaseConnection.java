import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection = null;
    private String driverInitialisation;
    private String driverConnection;
    private String user;
    private String password;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setDriverInitialisation(String driverInitialisation) {
        this.driverInitialisation = driverInitialisation;
    }

    public void setDriverConnection(String driverConnection) {
        this.driverConnection = driverConnection;
    }

    public Connection connect() {
        try {
            Class.forName(driverInitialisation);
            connection = DriverManager.getConnection(driverConnection, user, password);
            System.out.println("Connection established successfully!");
        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println("Exception thrown while trying to connect to database: " + exception.getMessage());
            exception.printStackTrace();
        }
        return connection;
    }
}
