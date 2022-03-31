package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import se.dzm.electricvehiclechargingstationmanagement.entity.BaseEntity;
import se.dzm.electricvehiclechargingstationmanagement.exception.ResourceNotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.mapping.BaseMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.BaseModel;
import se.dzm.electricvehiclechargingstationmanagement.model.PageModel;
import se.dzm.electricvehiclechargingstationmanagement.model.Select2Model;
import se.dzm.electricvehiclechargingstationmanagement.repository.BaseRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.BaseService;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseServiceImpl<M extends BaseModel<ID>, E extends BaseEntity<ID>, ID extends Serializable> implements BaseService<M, ID> {

    public final BaseRepository<E, ID> repository;
    public final BaseMapper<M, E> mapper;

    public BaseServiceImpl(BaseRepository<E, ID> repository, BaseMapper<M, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public abstract Predicate queryBuilder(M filter);

    @Override
    @Transactional(readOnly = true)
    public Page<M> findAll(M filter, Pageable pageable) {
        return repository.findAll(queryBuilder(filter), pageable).map(mapper::toModel);
    }

    @Override
    @Transactional(readOnly = true)
    public PageModel findAllTable(M filter, Pageable pageable) {
        Predicate predicate = queryBuilder(filter);
        Page<E> page = repository.findAll(predicate, pageable);

        return new PageModel(repository.count(), page.getTotalElements(), mapper.toModel(page.getContent()));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Select2Model> findAllSelect(M filter, Pageable pageable) {
        Predicate predicate = queryBuilder(filter);
        return repository.findAll(predicate, pageable).map(m -> new Select2Model(m.getId().toString(), m.getSelectTitle()));
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll(M filter) {
        return repository.count(queryBuilder(filter));
    }

    @Override
    @Transactional(readOnly = true)
    public M findById(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id: " + id));
        return mapper.toModel(entity);
    }

    @Override
    public M save(M model) {
        if (Objects.nonNull(model.getId())) {
            E entity = repository.findById(model.getId()).orElseThrow(() -> new ResourceNotFoundException("id: " + model.getId()));
            return mapper.toModel(repository.save(mapper.updateEntity(model, entity)));
        }
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public void deleteById(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id: " + id));
        repository.delete(entity);
    }
}
