package interfaces;

import java.util.List;

public interface CRUDRepository<ID,E> {
    void Add(E entity);
    void remove(ID id);
    void update(ID id,E newEntity);
    E findByID(ID id);
    List<E> findAll();
}
