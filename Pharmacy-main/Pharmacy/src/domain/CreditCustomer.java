package domain;

import java.util.List;

public class CreditCustomer extends Customer {
    private List<Drug> items;

    public CreditCustomer(String firstName, String lastName, Type type, int ID) {
        super(firstName, lastName, type, ID);
    }

    public void addIntoOrder(Drug drug){
        items.add(drug);
    }

    public List<Drug> getItems() {
        return items;
    }

    public void setItems(List<Drug> items) {
        this.items = items;
    }
}
