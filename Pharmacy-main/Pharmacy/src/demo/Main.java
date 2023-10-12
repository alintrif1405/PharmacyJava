package demo;
import DatabaseRepository.DatabaseRepository;
import controllers.CreditorController;
import controllers.DebitorController;
import controllers.DrugController;
import repository.inmemory.CreditorRepository;
import repository.inmemory.DebitorRepository;
import repository.inmemory.DrugRepository;
import view.Viewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Database or in memory?(0/1)");
        Scanner option=new Scanner(System.in);
        int opt=option.nextInt();
        if(opt==0){

            CreditorRepository CreditorRepo=new CreditorRepository();
            DebitorRepository DebitorRepo=new DebitorRepository();
            DrugRepository DrugRepo=new DrugRepository();
            DatabaseRepository databaseRepository=new DatabaseRepository(DrugRepo,CreditorRepo,DebitorRepo);
            CreditorController creditorController=new CreditorController(CreditorRepo);
            DebitorController debitorController=new DebitorController(DebitorRepo);
            DrugController drugController=new DrugController(DrugRepo);
            Viewer View=new Viewer(creditorController,drugController,debitorController);
            databaseRepository.load();
            View.menu();
            //databaseRepository.store();
        }
        else if(opt==1){
            CreditorRepository CreditorRepo=new CreditorRepository();
            DebitorRepository DebitorRepo=new DebitorRepository();
            DrugRepository DrugRepo=new DrugRepository();
            CreditorController CreditorController=new CreditorController(CreditorRepo);
            DebitorController DebitorController=new DebitorController(DebitorRepo);
            DrugController DrugController=new DrugController(DrugRepo);
            Viewer View=new Viewer(CreditorController,DrugController,DebitorController);
            View.menu();

            //...
        }


       // object.menu();



    }

}

