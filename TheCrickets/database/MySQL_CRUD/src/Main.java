public class Main {
    public static void main(String[] args) {
        CRUD_operations operations = new CRUD_operations();
        /*operations.insertUser("Petru", "Martincu", "martincu.petru@gmail.com", "pass123");
        operations.insertUser("Emilia", "Sfirnaciuc", "emilia.sfirnaciuc@gmail.com", "2018");
        operations.insertUser("Lucian", "Covaliu", "lucian.covaliu@gmai.com", "abcdef");
        operations.insertUser("Andreea", "Dascalu", "andreea.dascalu@gmail.com", "12345");
        System.out.println(operations.checkUserExists("martincu.petru@gmail.com", "pass123"));
        System.out.println(operations.readUserData("martincu.petru@gmail.com").toString());*/
        operations.insertUserByEmailPassword("martincu.petru@gmail.com", "12345");
        operations.deleteAllUsers();
    }
}
