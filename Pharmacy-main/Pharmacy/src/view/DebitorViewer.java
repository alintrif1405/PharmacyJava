package view;

import controllers.DebitorController;
import controllers.DrugController;
import domain.CreditCustomer;
import domain.DebitCustomer;
import domain.Drug;
import domain.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DebitorViewer {
    private final DebitorController debitorController;
    private final DrugController drugController;

    public DebitorViewer(controllers.DebitorController DebitorController,controllers.DrugController DrugController) {
        debitorController = DebitorController;
        drugController = DrugController;
    }

    public void menuDisplayViewer(){
        System.out.println("""
                Pick an action you would like to do as a Debitor: """);
        System.out.println("""
                1.Show all Drugs
                2.Purchase Drug
                0. Exit
                """);
    }

    public void menuDebitor(){
        Scanner debitorScanner = new Scanner(System.in);
        DebitorViewer debitorViewer = new DebitorViewer(debitorController,drugController);
        System.out.println("Insert your last name :");
        String lastNameDebitor = debitorScanner.nextLine();
        System.out.println("Insert your first name :");
        String firstNameDebitor = debitorScanner.nextLine();
        Type type = Type.ADULT;
        int id = 0;
        DebitCustomer customer = new DebitCustomer(firstNameDebitor,lastNameDebitor,type,id);
        if(!debitorController.getAll().contains(customer)){
            System.out.println("You are in need of an account");
            customer.setID(this.debitorController.getAll().size());
            debitorController.add(customer);
        }
        boolean ok = true;
        while(ok){
            menuDisplayViewer();
            switch (debitorScanner.nextInt()){
                case 1:
                    System.out.println("This is a list of all the drugs in the pharmacy");
                    System.out.println(drugController.getAll().toString());
                    break;
                case 2:
                    System.out.println("You've decided to purchase a drug \n ");
                    System.out.println(drugController.getAll().toString());
                    System.out.println("\n Decide what medication you would like to purchase after ID");
                    int idMed= debitorScanner.nextInt();
                    for(Drug drug :drugController.getAll())
                        if(drug.getID()==idMed) {
                            System.out.println("Insert the quantity of the medication");
                            int quantityMed = debitorScanner.nextInt();


                        }



                    float quantityWanted = debitorScanner.nextInt();
                    List<Drug> drugs = new ArrayList<>();
                    drugs=drugController.getAll();

                    break;

                case 0:
                    ok= false;
            }

        }




    }
}
