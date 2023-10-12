package view;

import domain.CreditCustomer;
import domain.Drug;
import domain.Type;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class AdminViewer {
    private final controllers.CreditorController creditorController;
    private final controllers.DrugController drugController;
    private final controllers.DebitorController debitorController;

    public AdminViewer(controllers.CreditorController CreditorController, controllers.DrugController DrugController, controllers.DebitorController DebitorController) {
        creditorController = CreditorController;
        drugController = DrugController;
        debitorController = DebitorController;
    }

    public void menuDisplayAdmin() {
        System.out.println("""
                Pick an action you would like to do as an Admin:\s
                """);
        System.out.println("""
                1. Add Creditor manually\s
                2. Delete Creditor manually
                3. Add Debitor manually
                4. Delete Debitor manually
                5. Show all Drugs sorted by name
                6. Show all Drugs sorted by price
                7. Show all Drugs sorted by quantity
                8. Manually  add QUANTITY/ create a Drug
                9. Manually  remove QUANTITY/ delete a Drug
                10. Show all Creditors
                11. Show all Debitors
                12. Request Medicine(In progress ...)
                0. Exit
                """);
    }

    public void menuDisplayNegativeValueDeletion() {
        System.out.println("""
                Looks like the value u entered causes the existence of a negative quantity
                In this case u have the following options:
                1.Set the QUANTITY to 0\s
                2.Remove the Drug\s
                3.Stop this action\s
                """);
    }

    public void menuAdmin() {
        Scanner adminScanner = new Scanner(System.in);
        AdminViewer adminViewer = new AdminViewer(creditorController, drugController, debitorController);
        String name;
        String lastName;
        Type type = null;
        int id;
        boolean ok = true;
        while (ok) {
            menuDisplayAdmin();
            switch (adminScanner.nextInt()) {
                case 1:
                    System.out.println("You've selected to enter a New Creditor manually");
                    System.out.println("Select a name: ");
                    name = adminScanner.next();
                    System.out.println("Select a last name: ");
                    lastName = adminScanner.next();
                    type = Type.SENIOR;

                    System.out.println(" Id will be automatically selected \n ");
                    id = this.creditorController.getAll().size();
                    adminViewer.creditorController.add(new CreditCustomer(name, lastName, type, id));
                    break;
                case 2:
                    System.out.println("You've selected to delete a Creditor manually");
                    System.out.println(Arrays.toString(creditorController.getAll().toString().split("}, ")));
                    System.out.println("Select the id : ");
                    int indexCreditor = adminScanner.nextInt();
                    creditorController.getAll().remove(indexCreditor - 1);
                    break;
                case 3:
                    System.out.println("You've selected to enter a New Debitor manually");

                    System.out.println("Select a name: ");
                    name = adminScanner.next();
                    System.out.println("Select a last name: ");
                    lastName = adminScanner.next();
                    int number = adminScanner.nextInt();
                    if (number == 0)
                        type = Type.CHILD;
                    else if (number == 1) {
                        type = Type.ADULT;
                    } else if (number == 2) {
                        type = Type.SENIOR;
                    }
                    System.out.println(" Id will be automatically selected \n ");
                    id = this.creditorController.getAll().size();
                    adminViewer.creditorController.add(new CreditCustomer(name, lastName, type, id));

                    break;
                case 4:
                    System.out.println("You've selected to delete a Debitor manually");
                    System.out.println(Arrays.toString(creditorController.getAll().toString().split("}, ")));
                    System.out.println("Select the id : ");
                    int indexDebit = adminScanner.nextInt();
                    creditorController.getAll().remove(indexDebit - 1);
                    break;
                case 5:
                    System.out.println("You've selected to see all Drugs sorted by name");
                    System.out.println(this.drugController.sortedByName());
                    break;
                case 6:
                    System.out.println("You've selected to see all Drugs sorted by price");
                    System.out.println(this.drugController.sortedByPrice());
                    break;
                case 7:
                    System.out.println("You've selected to see all Drugs sorted by quantity");
                    System.out.println(this.drugController.sortedByQuantity());
                case 8:
                    System.out.println("""
                            You've selected to create a new Drug.\s
                            Take note that if the drug already exists the quantity will be added and no new Drug will be created:\s
                            IMPORTANT NOTE: EACH DRUG HAS A UNIQUE NAME AND A UNIQUE ID \s
                            """);
                    System.out.println("Insert the name of the Drug u want to add: ");
                    String nameMedication = adminScanner.next();
                    boolean modified = false;
                    for (Drug e : drugController.getAll())
                        if (Objects.equals(e.getName(), nameMedication)) {
                            System.out.println("Looks like we've found the med you were looking for \nInsert the quantity you want to add");
                            int quantityMedication = adminScanner.nextInt();
                            while (quantityMedication < 0) {
                                System.out.println("You've inserted a negative value for quantity. Please insert a positive one");
                                quantityMedication = adminScanner.nextInt();
                            }
                            e.setQuantity(e.getQuantity() + quantityMedication);
                            modified = true;
                            break;
                        }
                    if (!modified) {
                        System.out.println("Insert the QUANTITY you want to add");
                        int quantityMedication = adminScanner.nextInt();
                        while (quantityMedication < 0) {
                            System.out.println("You've inserted a negative value for quantity. Please insert a positive one");
                            quantityMedication = adminScanner.nextInt();
                        }
                        System.out.println("Insert the PRICE you want to add");
                        float priceMedication = adminScanner.nextInt();
                        while (priceMedication < 0) {
                            System.out.println("You've inserted a negative value for price. Please insert a positive one");
                            priceMedication = adminScanner.nextInt();
                        }
                        Drug drug = new Drug(priceMedication, quantityMedication, nameMedication, drugController.getAll().size());
                        drugController.add(drug);
                    }
                    break;
                case 9:
                    System.out.println("""
                            You've selected to delete a Drug.\s
                            Take note that you can delete a Drug or lower it's quantity if you desire to do so:\s
                            IMPORTANT NOTE: EACH DRUG HAS A UNIQUE NAME AND A UNIQUE ID \s
                            """);
                    System.out.println("Insert the name of the Drug you would like to delete or lower its quantity");
                    nameMedication = adminScanner.next();
                    System.out.println("If you want to delete the Drug, set any negative value, otherwise just the value u want to remove: ");
                    int quantityMedication = adminScanner.nextInt();
                    for (Drug e : drugController.getAll())
                        if (Objects.equals(e.getName(), nameMedication)) {
                            if (quantityMedication < 0)
                                drugController.remove(e);
                            if (e.getQuantity() - quantityMedication >= 0)
                                e.setQuantity(e.getQuantity() - quantityMedication);
                            else if (e.getQuantity() - quantityMedication < 0) {
                                menuDisplayNegativeValueDeletion();
                                switch (adminScanner.nextInt()) {
                                    case 1 -> {
                                        e.setQuantity(0);
                                        System.out.println("The Quantity has been set on 0");
                                    }
                                    case 2 -> {
                                        drugController.remove(e);
                                        System.out.println("The Drug has been deleted");
                                    }
                                    case 3 -> System.out.println("Nothing happened");
                                }

                            }
                            break;
                        }

                    break;
                case 10:
                    System.out.println(creditorController.getAll().toString());
                    break;
                case 11:
                    System.out.println(debitorController.getAll().toString());
                    break;
                case 12:
                    System.out.println("You've decided to request some medicine");
                case 0:
                    ok = false;
                    Viewer view = new Viewer(creditorController, drugController, debitorController);
                    view.menu();

            }
        }
    }
}
