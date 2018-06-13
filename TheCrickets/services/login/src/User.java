import java.sql.Date;

/**
 * The User class is used to get and set the information of a user
 */
public class User {
    /**
     *  id of a user
     */
    public int id;
    /**
     *  the name  of a user
     */
    public String firstName;
    /**
     *  the last name of a user
     */
    public String lastName;
    /**
     * the email of a user
     */
    public String email;
    /**
     *  the password  of a user
     */
    public String password;
    /**
     *  date of Birth  of a user
     */
    public Date dateOfBirth;
    /**
     *  the phone number of a user
     */
    public String phoneNumber;

    User(){}

    User(int id, String firstName, String lastName, String email, String password, Date dateOfBirth, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * to get the name of a user
     * @return the first name of a user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * To set a name of a user
     * @param firstName the first name of a user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return (id + " " + firstName + " " + lastName + " " + email + " " + password + " " + dateOfBirth + " " + phoneNumber);
    }

}
