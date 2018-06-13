public class Contact {

    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private String comment = null;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String email, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email + " " + comment;
    }
}
