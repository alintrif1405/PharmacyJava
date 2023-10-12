package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DBCRUDRepository<ID,E> {
    void Add(E entity) throws SQLException;
    void remove(ID id) throws SQLException;
    void update(ID id,E newEntity) throws  SQLException;
    E findByID(ID id) throws SQLException;
    List<E> findAll() throws SQLException;
}
