package DatabaseRepository;

import domain.CreditCustomer;
import domain.DebitCustomer;
import domain.Drug;
import domain.Type;
import repository.inmemory.CreditorRepository;
import repository.inmemory.DebitorRepository;
import repository.inmemory.DrugRepository;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseRepository {
    private final DrugRepository drugRepository;
    private final CreditorRepository creditorRepository;
    private final DebitorRepository debitorRepository;
    private final Connection connection;
    public DatabaseRepository(DrugRepository drugRepository, CreditorRepository creditorRepository, DebitorRepository debitorRepository) throws SQLException{
        this.drugRepository = drugRepository;
        this.creditorRepository = creditorRepository;
        this.debitorRepository = debitorRepository;
        this.connection=DriverManager.getConnection("jdbc:jtds:sqlserver://DESKTOP-9OVL9C1/Pharmacy");//"jdbc:sqlserver://DESKTOP-9OVL9C1;databaseName=Pharmacy;user=guest;password=bazadedate;encrypt=true;trustServerCertificate=true");

    }
    public void store() throws SQLException {
        String sql = "INSERT INTO Drugs (id, price, quantity,name) VALUES (?, ?, ?, ?)";
        String sql1= "INSERT INTO Creditors (id,firstName,lastName,type) VALUES (?,?,?,?)";
        String sql2= "INSERT INTO Debitors(id,firstName,lastName,type) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        PreparedStatement statement1=connection.prepareStatement(sql1);
        PreparedStatement statement2=connection.prepareStatement(sql2);
        for(Drug d:this.drugRepository.findAll()) {

            statement.setInt(1, d.getID());
            statement.setFloat(2, d.getPrice());
            statement.setInt(3, d.getQuantity());
            statement.setString(4, d.getName());
            statement.executeUpdate();
        }
        for(CreditCustomer c:this.creditorRepository.findAll()) {

            statement1.setInt(1, c.getID());
            statement1.setString(2, c.getFirstName());
            statement1.setString(3, c.getLastName());
            statement1.setString(4,c.getType().toString());
            statement1.executeUpdate();
        }
        for (DebitCustomer d : this.debitorRepository.findAll()) {
            statement2.setInt(1, d.getID());
            statement2.setString(2, d.getFirstName());
            statement2.setString(3, d.getLastName());
            statement2.setString(4,d.getType().toString());
            statement2.executeUpdate();
        }


    }
    public void load() throws SQLException {
        String sql = "SELECT * FROM Drugs";
        String sql1 = "SELECT * FROM Creditors";
        String sql2 = "SELECT * FROM Debitors";

        PreparedStatement statement = connection.prepareStatement(sql);
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        PreparedStatement statement2 = connection.prepareStatement(sql2);

        ResultSet resultSet = statement.executeQuery();
        ResultSet resultSet1 = statement1.executeQuery();
        ResultSet resultSet2 = statement2.executeQuery();

        List<Drug> drugList = new ArrayList<>();
        List<CreditCustomer> creditCustomerList = new ArrayList<>();
        List<DebitCustomer> debitCustomerList = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            float price = resultSet.getFloat("price");
            int quantity = resultSet.getInt("quantity");
            String name = resultSet.getString("name");
            Drug drug = new Drug(price, quantity, name, id);
            drugList.add(drug);
        }
        while (resultSet1.next()) {
            int id = resultSet1.getInt("ID");
            String firstName = resultSet1.getString("firstName");
            String lastName = resultSet1.getString("lastName");
            Type type = Type.valueOf(resultSet1.getString("Type"));
            CreditCustomer creditCustomer = new CreditCustomer(firstName, lastName, type, id);
            creditCustomerList.add(creditCustomer);
        }
        while (resultSet2.next()) {
            int id = resultSet2.getInt("ID");
            String firstName = resultSet2.getString("firstName");
            String lastName = resultSet2.getString("lastName");
            Type type = Type.valueOf(resultSet2.getString("Type"));
            DebitCustomer debitCustomer = new DebitCustomer(firstName, lastName, type, id);
            debitCustomerList.add(debitCustomer);
        }
        this.drugRepository.setListOfDrugs(drugList);
        this.creditorRepository.setListOfCostumers(creditCustomerList);
        this.debitorRepository.setListOfDebitors(debitCustomerList);
    }
}
