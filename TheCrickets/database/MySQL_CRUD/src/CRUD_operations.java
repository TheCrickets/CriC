import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
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

    boolean checkSessionIDValid(int userID, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            String query = "SELECT expiringDate FROM sessionID JOIN users ON sessionID.userID = users.id WHERE users.id = ? and users.email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return false;
            else {
                if (resultSet.getTimestamp(1).before(new Timestamp(System.currentTimeMillis())))
                    return false;
                else return true;
            }
        } catch (SQLException exception) {
            System.err.println("Error at checking session for validity: " + exception.getMessage());
        }
        return false;
    }

    public void insertUser(String firstName, String lastName, String email, String password, Date dateOfBirth, String adress)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            String query = "INSERT INTO users(firstName, lastName, email, password, dateOfBirth, adress) VALUES(?, ?, ?, ?, DATE_SUB(DATE_SUB(DATE_SUB(?, INTERVAL 1 MONTH ), INTERVAL 1900 YEAR), INTERVAL -1 DAY), ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(5, dateOfBirth);
            preparedStatement.setString(6, adress);
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
        insertUser("", "", email, password, null, null);
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
            user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
            return user;

        } catch (SQLException exception)
        {
            System.err.println("Error while trying to get data from database: " + exception.getMessage());
        }
        return new User(0, null, null, null, null, null, null);
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

    public void updateUserData(int id, String firstName, String lastName, String email, String password, Date dateOfBirth, String adress)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, dateOfBirth = ?, adress = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(5, dateOfBirth);
            preparedStatement.setString(6, adress);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.shaHex(hash);
            preparedStatement.setString(4, sha256hex);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        } catch (NoSuchAlgorithmException exception)
        {
            System.err.println("Error while trying to encrypt the password: " + exception.getMessage());
        }
    }

   /* public void updateUserData(int id, String firstName, String lastName)
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
    }*/

    public void deleteAllUsers()
    {
        PreparedStatement preparedStatement = null;

        try
        {
            String queryZero = "SET FOREIGN_KEY_CHECKS=0";
            preparedStatement = connection.prepareStatement(queryZero);
            preparedStatement.executeUpdate();

            String queryOne = "TRUNCATE TABLE sessionID";
            preparedStatement = connection.prepareStatement(queryOne);
            preparedStatement.executeUpdate();

            String queryTwo = "TRUNCATE TABLE users";
            preparedStatement = connection.prepareStatement(queryTwo);
            preparedStatement.executeUpdate();

            String queryThree = "SET FOREIGN_KEY_CHECKS=1";
            preparedStatement = connection.prepareStatement(queryThree);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.err.println("Error while trying deleteUser all information from database: " + exception.getMessage());
        }
    }
}
