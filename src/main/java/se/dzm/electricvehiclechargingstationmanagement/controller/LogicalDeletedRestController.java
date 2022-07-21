package se.dzm.electricvehiclechargingstationmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.dzm.electricvehiclechargingstationmanagement.entity.LogicalDeleted;
import se.dzm.electricvehiclechargingstationmanagement.service.LogicalDeletedService;

import java.io.Serializable;

/**
 * @author Behrooz Mohamadi
 * @email behroooz.mohamadi@gmail.com
 * @date 3/27/2018 11:42 AM
 */
public interface LogicalDeletedRestController<E extends LogicalDeleted,ID extends Serializable> {

    LogicalDeletedService<E,ID> getService();

    @DeleteMapping(value = {"/logical-deleteById/{id}"})
    default ResponseEntity<Void> logicalDeleteById(@PathVariable("id") ID id){
        getService().logicalDeleteById(id);
        return ResponseEntity.noContent().build();
    }
}
