package domain;

import java.util.Date;
import java.util.List;

public class Order {
    Date orderDate;
    List<Drug> drugList;
    int orderID;

    public Order(Date date,int
                 ID){
        orderDate=date;
        orderID=ID;
    }
    public void addDrugsToOrder(Drug drug){
        drugList.add(drug);
    }

}
