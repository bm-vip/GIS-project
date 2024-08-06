package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.QCompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.filter.CompanyFilter;
import se.dzm.electricvehiclechargingstationmanagement.mapping.CompanyMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.CompanyService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<CompanyFilter, CompanyModel, CompanyEntity, Long> implements CompanyService {
    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    public JpaRepository<CompanyEntity,Long> getRepository() {
        return repository;
    }

    @Override
    public Page<CompanyModel> findAllByParentId(Long parentId, Pageable pageable) {
        return repository.findByParentId(parentId, pageable).map(mapper::toModel);
    }

    @Override
    public Predicate queryBuilder(CompanyFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QCompanyEntity path = QCompanyEntity.companyEntity;

        filter.getId().ifPresent(value -> builder.and(path.id.eq(value)));
        filter.getParentId().ifPresent(value -> builder.and(path.parent.id.eq(value)));
        filter.getName().ifPresent(value -> builder.and(path.name.contains(value)));

        return builder;
    }
}
