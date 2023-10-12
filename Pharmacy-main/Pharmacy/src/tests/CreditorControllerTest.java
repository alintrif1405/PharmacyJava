package tests;

import controllers.CreditorController;
import controllers.DebitorController;
import domain.CreditCustomer;
import domain.DebitCustomer;
import domain.Type;
import org.junit.jupiter.api.Test;
import repository.inmemory.CreditorRepository;
import repository.inmemory.DebitorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditorControllerTest {

    @Test
    void sortedByName() {
        CreditorRepository creditorRepository = new CreditorRepository();
        CreditorController creditorController = new CreditorController(creditorRepository);

        CreditCustomer creditor1 = new CreditCustomer("Alice", "Smith", Type.ADULT, 1 );
        CreditCustomer creditor2 = new CreditCustomer("Bob", "Johnson", Type.ADULT, 2 );
        CreditCustomer creditor3 = new CreditCustomer("Charlie", "Williams", Type.ADULT, 3);

        creditorController.add(creditor1);
        creditorController.add(creditor2);
        creditorController.add(creditor3);

        List<CreditCustomer> creditors = creditorController.sortedByName();
        assertEquals(3, creditors.size());
        assertEquals("Alice", creditors.get(0).getFirstName());
        assertEquals("Bob", creditors.get(1).getFirstName());
        assertEquals("Charlie", creditors.get(2).getFirstName());
    }

    @Test
    void add() {
        CreditorRepository creditorRepository = new CreditorRepository();
        CreditorController creditorController = new CreditorController(creditorRepository);

        CreditCustomer creditor1 = new CreditCustomer("Alice", "Smith", Type.ADULT, 1 );
        CreditCustomer creditor2 = new CreditCustomer("Bob", "Johnson", Type.ADULT, 2 );
        CreditCustomer creditor3 = new CreditCustomer("Charlie", "Williams", Type.ADULT, 3);

        assertTrue(creditorController.add(creditor1));
        creditorController.add(creditor2);
        creditorController.add(creditor3);


        assertEquals(3, creditorController.getAll().size());
        assertTrue(creditorController.getAll().contains(creditor3));

    }

    @Test
    void remove() {
        CreditorRepository creditorRepository = new CreditorRepository();
        CreditorController creditorController = new CreditorController(creditorRepository);

        CreditCustomer creditor1 = new CreditCustomer("Alice", "Smith", Type.ADULT, 1 );
        CreditCustomer creditor2 = new CreditCustomer("Bob", "Johnson", Type.ADULT, 2 );
        CreditCustomer creditor3 = new CreditCustomer("Charlie", "Williams", Type.ADULT, 3);

        creditorController.add(creditor1);
        creditorController.add(creditor2);
        creditorController.add(creditor3);

        assertTrue(creditorController.remove(creditor1));
        assertEquals(2, creditorController.getAll().size());
        assertTrue(creditorController.getAll().contains(creditor3));
    }

    @Test
    void update() {
        DebitorRepository debitorRepository = new DebitorRepository();
        DebitorController debitorController = new DebitorController(debitorRepository);

        DebitCustomer debitor1 = new DebitCustomer("Alice", "Smith", Type.ADULT, 1, 1000);
        debitorController.add(debitor1);
        DebitCustomer debitor1Updated = new DebitCustomer("Alice", "New", Type.ADULT, 1, 2000);
        assertTrue(debitorController.update(1, debitor1Updated));
        assertEquals("New",debitorController.getAll().get(0).getLastName());
        assertNotEquals(debitor1, debitorRepository.findByID(1));
    }
}