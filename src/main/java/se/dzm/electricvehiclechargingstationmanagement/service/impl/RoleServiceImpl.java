package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.QRoleEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.RoleEntity;
import se.dzm.electricvehiclechargingstationmanagement.exception.NotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.mapping.RoleMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.RoleRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;

import static se.dzm.electricvehiclechargingstationmanagement.util.MapperHelper.option;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleModel, RoleEntity, Long> implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    public Predicate queryBuilder(RoleModel filter) {
        QRoleEntity p = QRoleEntity.roleEntity;
        BooleanBuilder builder = new BooleanBuilder();

        option(() -> filter.getId()).ifPresent(id -> builder.and(p.id.eq(id)));
        if (StringUtils.hasLength(filter.getRole()))
            builder.and(p.role.eq(filter.getRole()));
        if (StringUtils.hasLength(filter.getTitle()))
            builder.and(p.title.contains(filter.getTitle()));
        return builder;
    }

    @Override
    public RoleModel findByRole(String role) {
        return mapper.toModel(repository.findByRole(role).orElseThrow(() -> new NotFoundException("role: " + role)));
    }
}