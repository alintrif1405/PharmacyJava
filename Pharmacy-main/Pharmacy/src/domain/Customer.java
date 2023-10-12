package domain;

public class Customer {
    private String firstName;
    private String lastName;
    Type type;
    private int ID;
    public Customer(String firstName, String lastName, Type type, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.ID = ID;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                ", ID=" + ID +
                '}';
    }
}
