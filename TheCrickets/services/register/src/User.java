
/**
 * The User class is used to get and set the information of a user
 */
public class User
{
    /**
     * the email of a user
     */
    String email;
    /**
     *  the password  of a user
     */
    String password;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    /**
     * to get the email of a user
     * @return the email of a user
     */
    public String getEmail() {
        return email;
    }
    /**
     * To set an email of a user
     * @param email the email of a user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * to get the password of a user
     * @return the password of a user
     */
    public String getPassword() {
        return password;
    }
    /**
     * To set a password of a user
     * @param password the password of a user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
