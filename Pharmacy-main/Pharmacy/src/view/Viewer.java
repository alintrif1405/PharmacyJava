package view;
import interfaces.ControllerInterface;
import controllers.DrugController;
import controllers.DebitorController;
import controllers.CreditorController;
import java.util.*;

import static java.lang.Thread.sleep;

public class Viewer {//trebuie puse controllere

    private final CreditorController CreditorController;
    private final DrugController DrugController;
    private final DebitorController DebitorController;

    public Viewer(controllers.CreditorController creditorController, controllers.DrugController drugController, controllers.DebitorController debitorController) {
        CreditorController = creditorController;
        DrugController = drugController;
        DebitorController = debitorController;
    }

    public void menuDisplayWelcome(){
        System.out.println("\tWelcome to your Pharmacy\n Please select your position :\n1. Admin \n2. Creditor \n3. Debitor \n");
        System.out.print("Type in the number : ");
    }
    public void menu() {
        Scanner first= new Scanner(System.in);
        menuDisplayWelcome();
        switch (first.nextInt()) {
            case 1 -> {
                System.out.println("You are now an admin");
                AdminViewer AdminView = new AdminViewer(CreditorController, DrugController, DebitorController);
                AdminView.menuAdmin();
            }
            case 2 -> {
                System.out.println("Your are now a creditor");
                CreditorViewer creditorView = new CreditorViewer(CreditorController, DrugController);
                creditorView.menuCreditor();
            }
            case 3 -> {
                System.out.println("You are now a debitor");
                DebitorViewer debitorView = new DebitorViewer(DebitorController, DrugController);
                debitorView.menuDebitor();
            }

        }
    }


}
