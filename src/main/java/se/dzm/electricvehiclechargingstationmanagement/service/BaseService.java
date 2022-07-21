package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.BaseModel;
import se.dzm.electricvehiclechargingstationmanagement.model.PageModel;
import se.dzm.electricvehiclechargingstationmanagement.model.Select2Model;

import java.io.Serializable;

public interface BaseService<M extends BaseModel<ID>, ID extends Serializable> {
    Page<M> findAll(M filter, Pageable pageable);
    PageModel findAllTable(M filter, Pageable pageable);
    Page<Select2Model> findAllSelect(M model, Pageable pageable);
    Long countAll(M filter);
    M findById(ID id);
    M save(M model);
    void deleteById(ID id);
}