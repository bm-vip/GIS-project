package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.QCompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.exception.ResourceNotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.mapping.CompanyMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.CompanyService;

import static se.dzm.electricvehiclechargingstationmanagement.util.MapperHelper.option;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<CompanyModel, CompanyEntity, Long> implements CompanyService {
    private CompanyRepository repository;
    private CompanyMapper mapper;

    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<CompanyModel> findByParentId(Long parentId, Pageable pageable) {
        return repository.findByParentId(parentId, pageable).map(mapper::toModel);
    }

    @Override
    public Predicate queryBuilder(CompanyModel filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QCompanyEntity path = QCompanyEntity.companyEntity;

        option(() -> filter.getId())
                .ifPresent(id -> builder.and(path.id.eq(id)));
        option(() -> filter.getParent().getId())
                .ifPresent(parentId -> builder.and(path.parent.id.eq(parentId)));
        if (StringUtils.hasText(filter.getName()))
            builder.and(path.name.contains(filter.getName()));

        return builder;
    }
}
