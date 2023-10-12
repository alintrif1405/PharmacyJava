package domain;
import java.util.*;


public class Drug {
    float price;
    int quantity;
    String name;
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Drug(float price, int numberOfPills, String name,int ID) {
        this.price = price;
        quantity = numberOfPills;
        this.name = name;
        this.ID=ID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
