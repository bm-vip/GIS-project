package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.BaseModel;
import se.dzm.electricvehiclechargingstationmanagement.model.PageModel;
import se.dzm.electricvehiclechargingstationmanagement.model.Select2Model;

import java.io.Serializable;

public interface BaseService<F, M extends BaseModel<ID>, ID extends Serializable> {
    Page<M> findAll(F filter, Pageable pageable);
    PageModel findAllTable(F filter, Pageable pageable);
    Page<Select2Model> findAllSelect(F filter, Pageable pageable);
    Long countAll(F filter);
    boolean exists(F filter);
    M findById(ID id);
    M create(M model);
    M update(M model);
    void deleteById(ID id);
}