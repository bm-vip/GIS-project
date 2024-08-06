package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.QUserEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.UserEntity;
import se.dzm.electricvehiclechargingstationmanagement.enums.RoleType;
import se.dzm.electricvehiclechargingstationmanagement.exception.BadRequestException;
import se.dzm.electricvehiclechargingstationmanagement.exception.NotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.filter.UserFilter;
import se.dzm.electricvehiclechargingstationmanagement.mapping.UserMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.model.UserModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.UserRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;
import se.dzm.electricvehiclechargingstationmanagement.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserFilter,UserModel, UserEntity, Long> implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserModel findByUserName(String userName) {
        return mapper.toModel(userRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("userName: " + userName)));
    }

    @Override
    public UserModel register(UserModel model) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(model.getUserName());
        if (optionalUserEntity.isPresent())
            throw new BadRequestException("userName is already taken!");

        if (StringUtils.hasLength(model.getPassword()))
            model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        model.setActive(true);
        RoleModel roleModel = roleService.findByRole(RoleType.USER);
        model.setRoles(new HashSet<>(Arrays.asList(roleModel)));
        return mapper.toModel(userRepository.save(mapper.toEntity(model)));
    }

    @Override
    @Transactional
    public UserModel update(UserModel model) {
        if (StringUtils.hasLength(model.getPassword()))
            model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));

        UserEntity entity = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException("id: " + model.getId()));
        return mapper.toModel(repository.save(mapper.updateEntity(model, entity)));
    }

    @Override
    @Transactional
    public UserModel create(UserModel model) {
        var entity = mapper.toEntity(model);
        if (StringUtils.hasLength(model.getPassword()))
            entity.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Predicate queryBuilder(UserFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QUserEntity path = QUserEntity.userEntity;

        if(!RoleType.hasRole(RoleType.ADMIN)) {
            builder.and(path.roles.any().role.ne(RoleType.ADMIN));
        }
        filter.getId().ifPresent(v -> builder.and(path.id.eq(v)));
        filter.getTitle().ifPresent(v -> builder.and(path.firstName.toLowerCase().contains(v.toLowerCase())).or(path.lastName.toLowerCase().contains(v.toLowerCase())));
        filter.getActive().ifPresent(v -> builder.and(path.active.eq(v)));
        filter.getUserName().ifPresent(v -> builder.and(path.userName.eq(v)));
        filter.getEmail().ifPresent(v -> builder.and(path.email.toLowerCase().eq(v.toLowerCase())));
        filter.getFirstName().ifPresent(v -> builder.and(path.firstName.toLowerCase().contains(v.toLowerCase())));
        filter.getLastName().ifPresent(v -> builder.and(path.lastName.toLowerCase().contains(v.toLowerCase())));

        return builder;
    }
}
