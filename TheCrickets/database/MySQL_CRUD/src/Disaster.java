import java.sql.Timestamp;

public class Disaster {
    int ID;
    String type;
    Timestamp time;
    float latitude;
    float longitude;

    public Disaster() {

    }

    public Disaster(int ID, String type, Timestamp time, float latitude, float longitude) {
        this.ID = ID;
        this.type = type;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return getID() + " " + getType() + " " + getTime() + " " + getLatitude() + " " + getLongitude();
    }
}
