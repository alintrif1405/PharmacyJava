package controllers;

import domain.CreditCustomer;
import domain.Drug;
import interfaces.ControllerInterface;
import repository.inmemory.DebitorRepository;
import interfaces.CRUDRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.DebitCustomer;
public class DebitorController implements ControllerInterface<Integer,DebitCustomer> {

    private final DebitorRepository DebitorRepository;

    public DebitorController(DebitorRepository debitorRepository) {
        DebitorRepository = debitorRepository;
    }

    @Override
    public List<DebitCustomer> getAll() {
        return this.DebitorRepository.findAll();
    }

    @Override
    public List<DebitCustomer> sortedByName() {
        List<DebitCustomer> copy=new ArrayList<>();
        copy.addAll(this.DebitorRepository.findAll());
        Collections.sort(copy,(o1,o2)->o1.getFirstName().compareTo(o2.getFirstName()));
        return copy;

    }

    @Override
    public boolean add(DebitCustomer Entity) {
        if(this.DebitorRepository.findByID(Entity.getID())==null){
            this.DebitorRepository.Add(Entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(DebitCustomer Entity) {
        if(this.DebitorRepository.findByID(Entity.getID())!=null){
            this.DebitorRepository.remove(Entity.getID());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Integer ID, DebitCustomer Entity) {
        if(this.DebitorRepository.findByID(Entity.getID())!=null){
            this.DebitorRepository.update(ID,Entity);
            return true;
        }
        return false;
    }
}
