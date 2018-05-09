public class User
{
    int id;
    String lastName;
    String firstName;
    String email;
    String password;

    public User(){}

    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getlastName() { return lastName; }

    public void setlastName(String lastName) { this.lastName = lastName; }

    public String getfirstName() { return firstName; }

    public void setfirstName(String firstName) { this.firstName = firstName; }

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
}
