package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dzm.electricvehiclechargingstationmanagement.entity.LogicalDeleted;
import se.dzm.electricvehiclechargingstationmanagement.exception.NotFoundException;

import java.io.Serializable;

public interface LogicalDeletedService<ID extends Serializable> {
    <E extends LogicalDeleted> JpaRepository<E, ID> getRepository();

    default void logicalDeleteById(ID id) {
        var entity = getRepository().findById(id).orElseThrow(() -> new NotFoundException("id: " + id));
        entity.setDeleted(true);
        getRepository().save(entity);
    }
}