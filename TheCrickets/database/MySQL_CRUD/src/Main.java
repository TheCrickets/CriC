public class Main {
    public static void main(String[] args) {
        CRUD_operations operations = new CRUD_operations();
        operations.insertData("Petru", "Martincu", "martincu.petru@gmail.com", "pass123");
        operations.insertData("Emilia", "Sfirnaciuc", "emilia.sfirnaciuc@gmail.com", "2018");
        operations.insertData("Lucian", "Covaliu", "lucian.covaliu@gmai.com", "abcdef");
        operations.insertData("Andreea", "Dascalu", "andreea.dascalu@gmail.com", "12345");
        System.out.println(operations.checkUser("martincu.petru@gmail.com", "pass123"));
        System.out.println(operations.readData("martincu.petru@gmail.com").toString());
    }
}
