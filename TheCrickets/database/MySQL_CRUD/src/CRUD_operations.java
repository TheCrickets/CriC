import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CRUD_operations
{

    private Connection connection;

    CRUD_operations()
    {
        //try
        //{
            //File file = new File("http://localhost:3000/static/databaseConfig.txt");
            //Scanner sc = new Scanner(file);
            DatabaseConnection databaseConnection = new DatabaseConnection();


            databaseConnection.setDriverInitialisation("com.mysql.cj.jdbc.Driver");
            databaseConnection.setDriverConnection("jdbc:mysql://138.68.64.239:3306/CRIC?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false");
            databaseConnection.setUser("test");
            databaseConnection.setPassword("");


            connection = databaseConnection.connect();
        //} catch (FileNotFoundException exception)
        //{
        //    System.err.println("Configuration file not foundd: " + exception.getMessage());
        //}
    }

    void insertDisaster(Disaster disaster) {
        try {
            PreparedStatement preparedStatement = null;
            String query = "INSERT INTO disasters VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, disaster.getID());
            preparedStatement.setString(2, disaster.getType());
            preparedStatement.setTimestamp(3, disaster.getTime());
            preparedStatement.setFloat(4, disaster.getLatitude());
            preparedStatement.setFloat(5, disaster.getLongitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
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


    ArrayList<Disaster> getAllEvents() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try
        {
            String query = "SELECT * FROM disasters";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Disaster> disasters = new ArrayList<>();
            while (resultSet.next()) {
                Disaster disaster = new Disaster();
                disaster.setID(resultSet.getInt(1));
                disaster.setType(resultSet.getString(2));
                disaster.setTime(resultSet.getTimestamp(3));
                disaster.setLatitude(resultSet.getFloat(4));
                disaster.setLongitude(resultSet.getFloat(5));
                disasters.add(disaster);
            }
            return disasters;
        } catch (SQLException exception) {
            System.err.println("Error at checking session for validity: " + exception.getMessage());
        }
        return null;
    }



    boolean checkSessionIDValid(String sessionID, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            String query = "SELECT expiringDate FROM sessionID JOIN users on sessionID.userID = users.id and users.email = ? WHERE sessionID.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, sessionID);
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

    public void insertUser(String firstName, String lastName, String email, String password, Date dateOfBirth, String phoneNumber)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            String query = "INSERT INTO users(firstName, lastName, email, password, dateOfBirth, phoneNumber) VALUES(?, ?, ?, ?, DATE_SUB(DATE_SUB(DATE_SUB(?, INTERVAL 1 MONTH ), INTERVAL 1900 YEAR), INTERVAL -1 DAY), ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(5, dateOfBirth);
            preparedStatement.setString(6, phoneNumber);
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
        insertUser("", "", email, password, new Date(1, 1,1), "");
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


    public void deleteSessionID(String sessionID)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "DELETE FROM sessionID WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sessionID);
            preparedStatement.executeUpdate();
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to deleteSessionID data from database: " + exception.getMessage());
        }
    }

    public boolean checkSessionExists(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            String query = "SELECT expiringDate FROM sessionID JOIN users on sessionID.userID = users.id and users.email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getTimestamp(1).after(new Timestamp(System.currentTimeMillis())))
                    return true;
            }
        } catch (SQLException exception) {
            System.err.println("Error at checking session for validity: " + exception.getMessage());
        }
        return false;
    }




    public void updateUserData(int id, String firstName, String lastName, Date dateOfBirth, String phoneNumber) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ?, dateOfBirth = ?, phoneNumber = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, dateOfBirth);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        }
    }

    public void updateUserData(int id, String firstName, String lastName, String email, String password, Date dateOfBirth, String phoneNumber)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, dateOfBirth = ?, phoneNumber = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(5, dateOfBirth);
            preparedStatement.setString(6, phoneNumber);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String sha256hex = org.apache.commons.codec.digest.DigestUtils.shaHex(hash);
            preparedStatement.setString(4, sha256hex);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        } catch (NoSuchAlgorithmException exception) {
            System.err.println("Error while trying to encrypt the password: " + exception.getMessage());
        }
    }

    public String addSessionForUser(int id)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            String query = "INSERT INTO sessionID(id, userID) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();
            preparedStatement.setString(1, randomUUIDString);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return randomUUIDString;
        } catch (SQLException exception)
        {
            System.err.println("Error while trying to updateUserData data from database: " + exception.getMessage());
        }
        return null;
    }

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
