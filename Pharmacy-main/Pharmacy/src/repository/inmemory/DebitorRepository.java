package repository.inmemory;

import domain.Customer;
import domain.DebitCustomer;
import interfaces.CRUDRepository;

import java.util.ArrayList;
import java.util.List;

public class DebitorRepository implements CRUDRepository<Integer,DebitCustomer> {

    private List<DebitCustomer> listOfDebitors;

    public DebitorRepository() {
        this.listOfDebitors = new ArrayList<DebitCustomer>();
    }

    @Override
    public void Add(DebitCustomer entity) {
    this.listOfDebitors.add(entity);
    }

    @Override
    public void remove(Integer ID) {
    this.listOfDebitors.removeIf(CD->CD.getID()==ID);
    }

    public void setListOfDebitors(List<DebitCustomer> listOfDebitors) {
        this.listOfDebitors = listOfDebitors;
    }

    @Override
    public void update(Integer ID, DebitCustomer newEntity) {
        this.listOfDebitors.removeIf(CD->CD.getID()==ID);
        this.listOfDebitors.add(newEntity);
    }

    @Override
    public DebitCustomer findByID(Integer ID) {
        for(DebitCustomer CD:this.listOfDebitors)
            if(CD.getID()==ID)
                return CD;
        return null;
    }

    @Override
    public List<DebitCustomer> findAll() {
        return this.listOfDebitors;
    }
}
