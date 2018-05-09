import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUD_operations
{

    private Connection connection;

    CRUD_operations()
    {
        try
        {

            //File file = new File(".");
            //for(String fileNames : file.list()) System.out.println(fileNames);

            File file = new File("databaseConfig.txt");
            Scanner sc = new Scanner(file);
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.setDriverInitialisation(sc.nextLine());
            databaseConnection.setDriverConnection(sc.nextLine());
            databaseConnection.setUser(sc.nextLine());
            databaseConnection.setPassword(sc.nextLine());
            connection = databaseConnection.connect();
        } catch (FileNotFoundException exception)
        {
            System.err.println("Configuration file not foundd: " + exception.getMessage());
        }
    }

    boolean checkUserExists(String email, String password)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            String query = "SELECT firstName, lastName FROM users WHERE email = ? and password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.shaHex(hash);
            preparedStatement.setString(2, sha256hex);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return false;
            else return true;
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to insert data into database: " + exception.getMessage());
        } catch (NoSuchAlgorithmException exception)
        {
            System.err.println("Error while checking the password: " + exception.getMessage());
        }
        return false;
    }

    public void insertUser(String firstName, String lastName, String email, String password)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            String query = "INSERT INTO users(firstName, lastName, email, password) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.shaHex(hash);
            preparedStatement.setString(4, sha256hex);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to insert data into database: " + exception.getMessage());
        } catch (NoSuchAlgorithmException exception)
        {
            System.err.println("Error at crypting the password: " + exception.getMessage());
        }
    }

    public void insertUserByEmailPassword(String email, String password)
    {
        insertUser("", "", email, password);
    }

    User readUserData(String email)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder result = new StringBuilder();
        User user;
        try
        {
            String query = "SELECT * FROM users WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            return user;

        } catch (SQLException exception)
        {
            System.err.println("Error while trying to get data from database: " + exception.getMessage());
        }
        return new User(0, "", "", "", "");
    }

    public void deleteUser(int id)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "DELETE FROM users WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to deleteUser data from database: " + exception.getMessage());
        }
    }

    public void updateUserData(int id, String firstName, String lastName, String email, String password)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.shaHex(hash);
            preparedStatement.setString(4, sha256hex);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        } catch (NoSuchAlgorithmException exception)
        {
            System.err.println("Error while trying to encrypt the password: " + exception.getMessage());
        }
    }

    public void updateUserData(int id, String firstName, String lastName)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        }
    }

    public void deleteAllUsers()
    {
        PreparedStatement preparedStatement = null;

        try
        {
            String query = "TRUNCATE TABLE users";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying deleteUser all information from database: " + exception.getMessage());
        }
    }
}
