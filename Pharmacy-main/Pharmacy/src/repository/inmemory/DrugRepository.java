package repository.inmemory;
import domain.Drug;
import interfaces.CRUDRepository;

import java.util.ArrayList;
import java.util.List;

public class DrugRepository implements CRUDRepository<Integer,Drug> {
    private List<Drug> listOfDrugs;

    public DrugRepository() {
        this.listOfDrugs = new ArrayList<>();
    }

    @Override
    public void Add(Drug entity) {
        this.listOfDrugs.add(entity);
    }

    @Override
    public void remove(Integer ID) {
        this.listOfDrugs.removeIf(Drug->Drug.getID()==ID);

    }

    public void setListOfDrugs(List<Drug> listOfDrugs) {
        this.listOfDrugs = listOfDrugs;
    }

    @Override
    public void update(Integer ID, Drug newEntity) {
        this.listOfDrugs.removeIf(Drug->Drug.getID()==ID);
        this.listOfDrugs.add(newEntity);

    }

    public List<Drug> getListOfDrugs() {
        return listOfDrugs;
    }

    @Override
    public Drug findByID(Integer ID) {
        for (Drug drug : listOfDrugs) {
            if (drug.getID()==ID) {
                return drug;
            }
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        return this.listOfDrugs;
    }
}
