import java.sql.Date;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        CRUD_operations operations = new CRUD_operations();
        operations.deleteAllUsers();
        operations.insertUser("Petru", "Martincu", "martincu.petru@gmail.com", "pass123", new Date(2018,5,9), "Adress_1");
        operations.insertUser("Emilia", "Sfirnaciuc", "emilia.sfirnaciuc@gmail.com", "2018", new Date(2018, 5,9), "Adress_1");
        operations.insertUser("Lucian", "Covaliu", "lucian.covaliu@gmai.com", "abcdef", new Date(2018, 5,9), "Adress_1");
        operations.insertUser("Andreea", "Dascalu", "andreea.dascalu@gmail.com", "12345", new Date(2018, 5,9), "Adress_1");
        System.out.println(operations.checkUserExists("martincu.petru@gmail.com", "pass123"));
        System.out.println(operations.readUserData("martincu.petru@gmail.com").toString());
        operations.insertUserByEmailPassword("martincu.petru@gmail.com", "12345");
        System.out.println(operations.checkSessionIDValid(1, "martincu.petru@gmail.com"));
        //operations.deleteAllUsers();
    }
}
