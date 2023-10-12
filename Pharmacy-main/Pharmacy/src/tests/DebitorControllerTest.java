package tests;

import controllers.DebitorController;
import domain.DebitCustomer;
import domain.Type;
import org.junit.jupiter.api.Test;
import repository.inmemory.DebitorRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DebitorControllerTest {


    @Test
    void sortedByName() {
        DebitorRepository debitorRepository = new DebitorRepository();
        DebitorController debitorController = new DebitorController(debitorRepository);

        DebitCustomer debitor1 = new DebitCustomer("Alice", "Smith", Type.ADULT, 1, 1000);
        DebitCustomer debitor2 = new DebitCustomer("Bob", "Johnson", Type.ADULT, 2, 2000);
        DebitCustomer debitor3 = new DebitCustomer("Charlie", "Williams", Type.ADULT, 3, 3000);

        debitorController.add(debitor1);
        debitorController.add(debitor2);
        debitorController.add(debitor3);

        List<DebitCustomer> debitors = debitorController.sortedByName();
        assertEquals(3, debitors.size());
        assertEquals("Alice", debitors.get(0).getFirstName());
        assertEquals("Bob", debitors.get(1).getFirstName());
        assertEquals("Charlie", debitors.get(2).getFirstName());
    }

    @Test
    void add() {
        DebitorRepository debitorRepository = new DebitorRepository();
        DebitorController debitorController = new DebitorController(debitorRepository);

        DebitCustomer debitor1 = new DebitCustomer("Alice", "Smith", Type.ADULT, 1, 1000);
        DebitCustomer debitor2 = new DebitCustomer("Bob", "Johnson", Type.ADULT, 2, 2000);
        DebitCustomer debitor3 = new DebitCustomer("Charlie", "Williams", Type.ADULT, 3, 3000);

        assertTrue(debitorController.add(debitor1));
        debitorController.add(debitor2);
        debitorController.add(debitor3);
        assertEquals(3, debitorController.getAll().size());
        assertTrue(debitorController.getAll().contains(debitor3));

    }

    @Test
    void remove() {
        DebitorRepository debitorRepository = new DebitorRepository();
        DebitorController debitorController = new DebitorController(debitorRepository);

        DebitCustomer debitor1 = new DebitCustomer("Alice", "Smith", Type.ADULT, 1, 1000);
        DebitCustomer debitor2 = new DebitCustomer("Bob", "Johnson", Type.ADULT, 2, 2000);
        DebitCustomer debitor3 = new DebitCustomer("Charlie", "Williams", Type.ADULT, 3, 3000);

        debitorController.add(debitor1);
        debitorController.add(debitor2);
        debitorController.add(debitor3);

        assertTrue(debitorController.remove(debitor3));
        assertEquals(2, debitorController.getAll().size());
        assertFalse(debitorController.getAll().contains(debitor3));
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