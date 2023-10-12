package repository.inmemory;
import domain.*;
import interfaces.CRUDRepository;
import domain.CreditCustomer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditorRepository implements CRUDRepository<Integer,CreditCustomer> {
    public List<CreditCustomer> getListOfCostumers() {
        return listOfCostumers;
    }

    public void setListOfCostumers(List<CreditCustomer> listOfCostumers) {
        this.listOfCostumers = listOfCostumers;
    }

    private List<CreditCustomer> listOfCostumers;

    public CreditorRepository() {
        this.listOfCostumers = new ArrayList<CreditCustomer>();
    }

    @Override
    public void Add(CreditCustomer entity) {
       // CreditCustomer creditCustomer = new CreditCustomer("John","Klaus","Type 0",1);
        listOfCostumers.add(entity);
    }

    @Override
    public void remove(Integer ID) {
        this.listOfCostumers.removeIf(CR -> CR.getID() == ID);

    }

    @Override
    public void update(Integer ID, CreditCustomer newEntity) {
        this.listOfCostumers.removeIf(CR -> CR.getID() == ID);
        this.listOfCostumers.add(newEntity);
    }

    @Override
    public CreditCustomer findByID(Integer ID) {
        for(CreditCustomer CR:this.listOfCostumers)
            if(CR.getID()==ID)
                return CR;

        return null;
    }

    @Override
    public List<CreditCustomer> findAll() {
        return this.listOfCostumers;
    }
}
