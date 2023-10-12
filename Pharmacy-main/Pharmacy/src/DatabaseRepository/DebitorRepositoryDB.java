package DatabaseRepository;

import domain.CreditCustomer;
import domain.Customer;
import domain.DebitCustomer;
import domain.Type;
import interfaces.CRUDRepository;
import interfaces.DBCRUDRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DebitorRepositoryDB implements DBCRUDRepository<Integer,DebitCustomer> {

    private final Connection connection;
    private final List<DebitCustomer> listOfDebitors;

    public DebitorRepositoryDB(Connection connection) {
        this.connection = connection;
        this.listOfDebitors = new ArrayList<DebitCustomer>();
    }

    @Override
    public void Add(DebitCustomer entity) throws SQLException {
        String sql = "INSERT INTO Debitors (id, firstName, lastName,type) VALUES (?, ?, ?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4,entity.getType().toString());
        statement.executeUpdate();
        this.listOfDebitors.add(entity);
    }

    @Override
    public void remove(Integer ID) throws SQLException {
        String sql = "DELETE FROM Debitors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        statement.executeUpdate();
        this.listOfDebitors.remove(this.findByID(ID));
    }

    @Override
    public void update(Integer ID, DebitCustomer newEntity) throws SQLException {
        String sql = "UPDATE Debitors SET firstName = ?, lastName = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newEntity.getFirstName());
        statement.setString(2, newEntity.getLastName());
        statement.setInt(3, ID);
        statement.executeUpdate();
        this.listOfDebitors.remove(this.findByID(ID));
        this.listOfDebitors.add(newEntity);

    }

    @Override
    public DebitCustomer findByID(Integer ID) throws SQLException {

        String sql = "SELECT * FROM Debitors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Type type = Type.valueOf(resultSet.getString("Type"));
            return new DebitCustomer(firstName, lastName, type, id);
        }   else {
            return null;
        }
    }
    public List<DebitCustomer> findAll() throws SQLException {
        String sql = "SELECT * FROM Debitors";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<DebitCustomer> debitors = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Type type = Type.valueOf(resultSet.getString("Type"));
            debitors.add(new DebitCustomer(firstName, lastName, type, id));
        }
        return debitors;
    }
    }


