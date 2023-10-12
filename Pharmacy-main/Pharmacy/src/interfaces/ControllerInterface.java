package interfaces;

import domain.Drug;

import java.util.List;

public interface ControllerInterface<Integer,E> {

    List<E> getAll();
    List<E> sortedByName();
    boolean add(E Entity);
    boolean remove(E Entity);
    boolean update(java.lang.Integer ID,E Entity);


}
