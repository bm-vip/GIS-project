package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import se.dzm.electricvehiclechargingstationmanagement.entity.QRoleEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.RoleEntity;
import se.dzm.electricvehiclechargingstationmanagement.enums.RoleType;
import se.dzm.electricvehiclechargingstationmanagement.exception.NotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.filter.RoleFilter;
import se.dzm.electricvehiclechargingstationmanagement.mapping.RoleMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.RoleRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleFilter,RoleModel, RoleEntity, Long> implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    public Predicate queryBuilder(RoleFilter filter) {
        QRoleEntity path = QRoleEntity.roleEntity;
        BooleanBuilder builder = new BooleanBuilder();

        if(!RoleType.hasRole(RoleType.ADMIN)) {
            builder.and(path.role.ne(RoleType.ADMIN));
        }

        filter.getId().ifPresent(value -> builder.and(path.id.eq(value)));
        filter.getRole().ifPresent(value -> builder.and(path.role.eq(value)));
        filter.getTitle().ifPresent(value -> builder.and(path.title.eq(value)));

        return builder;
    }

    @Override
    public RoleModel findByRole(String role) {
        return mapper.toModel(repository.findByRole(role).orElseThrow(() -> new NotFoundException("role: " + role)));
    }
}