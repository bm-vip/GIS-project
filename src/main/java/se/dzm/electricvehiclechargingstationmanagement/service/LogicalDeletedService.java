package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dzm.electricvehiclechargingstationmanagement.entity.LogicalDeleted;
import se.dzm.electricvehiclechargingstationmanagement.exception.ResourceNotFoundException;

import java.io.Serializable;

public interface LogicalDeletedService<E extends LogicalDeleted,ID extends Serializable> {
    JpaRepository<E, ID> getRepository();

    default void logicalDeleteById(ID id) {
        E entity = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("id: " + id));
        entity.setDeleted(true);
        getRepository().save(entity);
    }
}