package DatabaseRepository;

import domain.Drug;
import interfaces.CRUDRepository;
import interfaces.DBCRUDRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryDB implements DBCRUDRepository<Integer, Drug> {
    private final Connection connection;
    private final List<Drug> listOfDrugs;
    public DrugRepositoryDB(Connection connection,List<Drug> listOfDrugs) {
        this.connection = connection;
        this.listOfDrugs=listOfDrugs;
    }

    @Override
    public void Add(Drug entity) throws SQLException {
        String sql = "INSERT INTO drugs (id, price, quantity, name) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.getID());
        statement.setFloat(2, entity.getPrice());
        statement.setInt(3, entity.getQuantity());
        statement.setString(4, entity.getName());
        statement.executeUpdate();
        this.listOfDrugs.add(entity);
    }

    @Override
    public void remove(Integer ID) throws SQLException {
        String sql = "DELETE FROM drugs WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        statement.executeUpdate();
        this.listOfDrugs.remove(this.findByID(ID));
    }

    @Override
    public void update(Integer ID, Drug newEntity) throws SQLException {
        String sql = "UPDATE drugs SET price = ?, quantity = ?, name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setFloat(1, newEntity.getPrice());
        statement.setInt(2, newEntity.getQuantity());
        statement.setString(3, newEntity.getName());
        statement.setInt(4, ID);
        statement.executeUpdate();
        this.listOfDrugs.remove(this.findByID(ID));
        this.listOfDrugs.add(newEntity);
    }

    @Override
    public Drug findByID(Integer ID) throws SQLException {
        String sql = "SELECT * FROM drugs WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            int quantity = resultSet.getInt("Quantity");
            return new Drug(price, quantity, name, id);
        } else {
            return null;
        }
    }

    @Override
    public List<Drug> findAll() throws SQLException {
        String sql = "SELECT * FROM drugs";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Drug> drugs = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            int quantity = resultSet.getInt("Quantity");
            drugs.add(new Drug(price, quantity, name, id));
        }
        return drugs;
    }
}

