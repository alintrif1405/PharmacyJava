package tests;

import controllers.DrugController;
import domain.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.inmemory.DrugRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {

    }

    @Test
    void sortedByName() {
        DrugRepository drugRepository=new DrugRepository();
        DrugController drugController=new DrugController(drugRepository);
        Drug drug1 = new Drug(10, 20, "Aspirin",1);
        Drug drug2 = new Drug(20, 30, "Ibuprofen",2);
        drugController.add(drug2);

        drugController.add(drug1);
        List<Drug> drugs = drugController.sortedByName();
        assertEquals(2, drugs.size());
        assertEquals(drug1, drugs.get(0));
        assertEquals(drug2, drugs.get(1));
    }

    @Test
    void add() {
        DrugRepository drugRepository=new DrugRepository();
        DrugController drugController=new DrugController(drugRepository);
        Drug drug = new Drug(10, 20, "Aspirin",1);
        boolean result = drugController.add(drug);
        assertTrue(result);
        List<Drug> drugs = drugController.getAll();
        assertEquals(1, drugs.size());
        assertEquals(drug, drugs.get(0));
    }

    @Test
    void remove() {
        Drug drug = new Drug(10, 20, "Aspirin",1);
        Drug drug1 = new Drug(10, 20, "A",3);
        DrugRepository drugRepository=new DrugRepository();
        DrugController drugController=new DrugController(drugRepository);
        drugController.add(drug);
        drugController.add(drug1);
        boolean result = drugController.remove(drug);
        assertTrue(result);
        List<Drug> drugs = drugController.getAll();
        assertEquals(1, drugs.size());
    }

    @Test
    void update() {
        DrugRepository drugRepository=new DrugRepository();
        DrugController drugController=new DrugController(drugRepository);
        Drug drug = new Drug(10, 20, "Aspirin",1);
        drugController.add(drug);
        Drug newDrug = new Drug(20, 30, "Ibuprofen",3);
        boolean result = drugController.update(1, newDrug);
        assertTrue(result);
        List<Drug> drugs = drugController.getAll();
        assertEquals(1, drugs.size());
        assertEquals(newDrug, drugs.get(0));
    }
}