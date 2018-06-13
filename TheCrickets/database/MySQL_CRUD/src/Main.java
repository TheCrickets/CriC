

import java.sql.Timestamp;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Disaster disaster = new Disaster(7, "aluminu", new Timestamp(2018-1900, 06 - 1, 13, 19, 20, 28, 0), 2.1f, 5.3f);
        CRUD_operations operations = new CRUD_operations();

        operations.insertDisaster(disaster);

        ArrayList<Disaster> disasters = operations.getAllEvents();
        for (Disaster d : disasters) {
            System.out.println(d);
        }

    }
}
