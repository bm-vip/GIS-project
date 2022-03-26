package se.dzm.electricvehiclechargingstationmanagement.controller.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import se.dzm.electricvehiclechargingstationmanagement.controller.BaseRest;
import se.dzm.electricvehiclechargingstationmanagement.model.BaseModel;
import se.dzm.electricvehiclechargingstationmanagement.service.BaseService;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Behrooz Mohamadi
 * @email behroooz.mohamadi@gmail.com
 * @date 3/27/2018 11:42 AM
 */
public abstract class BaseRestImpl<M extends BaseModel<ID>, ID extends Serializable> implements BaseRest<M, ID> {
    protected BaseService<M, ID> service;
    protected Class<M> clazz;
    protected ObjectMapper objectMapper;

    public BaseRestImpl(BaseService<M, ID> service, Class<M> clazz) {
        this.clazz = clazz;
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ResponseEntity<M> findById(ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Page<M>> findAll(Optional<String> json, Pageable pageable) {
        M model = objectMapper.readValue(json.orElse("{}"), clazz);
        return ResponseEntity.ok(service.findAll(model, pageable));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Long> countAll(Optional<String> json) {
        M model = objectMapper.readValue(json.orElse("{}"), clazz);
        return ResponseEntity.ok(service.countAll(model));
    }

    @Override
    public ResponseEntity<Void> deleteById(ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<M> save(M model) {
        return ResponseEntity.ok(service.save(model));
    }

}
