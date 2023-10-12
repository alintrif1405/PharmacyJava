package DatabaseRepository;

import domain.CreditCustomer;
import domain.Type;
import interfaces.CRUDRepository;
import interfaces.DBCRUDRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditorRepositoryDB implements DBCRUDRepository<Integer, CreditCustomer> {

    private final Connection connection;
    private final List<CreditCustomer> creditors;

    public CreditorRepositoryDB(Connection connection,List<CreditCustomer> creditors) {
        this.connection = connection;
        this.creditors=creditors;
    }

    @Override
    public void Add(CreditCustomer entity) throws SQLException {
        String sql = "INSERT INTO creditors (id, firstName, lastName, type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getType().toString());
        statement.executeUpdate();
        this.creditors.add(entity);
    }

@Override
    public void update(Integer ID, CreditCustomer newEntity) throws SQLException {
        String sql = "UPDATE creditors SET firstName = ?, lastName = ?, type = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newEntity.getFirstName());
        statement.setString(2, newEntity.getLastName());
        statement.setString(3, newEntity.getType().toString());
        statement.setInt(4, ID);
        statement.executeUpdate();
        this.creditors.remove(this.findByID(ID));
        this.creditors.add(newEntity);
        }

        @Override
    public CreditCustomer findByID(Integer ID) throws SQLException {
        String sql = "SELECT * FROM creditors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Type type = Type.valueOf(resultSet.getString("Type"));
            return new CreditCustomer(firstName, lastName, type, id);
            }   else {
                    return null;
            }
        }

    @Override
    public List<CreditCustomer> findAll() throws SQLException {
        String sql = "SELECT * FROM creditors";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<CreditCustomer> creditors = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Type type = Type.valueOf(resultSet.getString("Type"));
            creditors.add(new CreditCustomer(firstName, lastName, type, id));
        }
        return creditors;
    }
    @Override
    public void remove(Integer ID) throws SQLException {
        String sql = "DELETE FROM creditors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        statement.executeUpdate();
        this.creditors.remove(this.findByID(ID));
    }


}

