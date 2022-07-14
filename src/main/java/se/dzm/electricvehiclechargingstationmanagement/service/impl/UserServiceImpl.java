package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.QUserEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.UserEntity;
import se.dzm.electricvehiclechargingstationmanagement.enums.RoleType;
import se.dzm.electricvehiclechargingstationmanagement.exception.BadRequestException;
import se.dzm.electricvehiclechargingstationmanagement.exception.ResourceNotFoundException;
import se.dzm.electricvehiclechargingstationmanagement.mapping.UserMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.model.UserModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.UserRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;
import se.dzm.electricvehiclechargingstationmanagement.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static se.dzm.electricvehiclechargingstationmanagement.util.MapperHelper.option;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, UserEntity, Long> implements UserService {

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
        return mapper.toModel(userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("userName: " + userName)));
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
    public UserModel save(UserModel model) {
        if (StringUtils.hasLength(model.getPassword()))
            model.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        if (Objects.nonNull(model.getId())) {
            UserEntity entity = repository.findById(model.getId()).orElseThrow(() -> new ResourceNotFoundException("id: " + model.getId()));
            return mapper.toModel(repository.save(mapper.updateEntity(model, entity)));
        }
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public Predicate queryBuilder(UserModel filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QUserEntity user = QUserEntity.userEntity;

        option(() -> filter.getId()).ifPresent(id -> builder.and(user.id.eq(id)));
        option(() -> filter.getActive()).ifPresent(active -> builder.and(user.active.eq(active)));

        if (StringUtils.hasLength(filter.getUserName()))
            builder.and(user.userName.eq(filter.getUserName()));
        if (StringUtils.hasLength(filter.getFirstName()))
            builder.and(user.firstName.contains(filter.getFirstName()));
        if (StringUtils.hasLength(filter.getLastName()))
            builder.and(user.lastName.contains(filter.getLastName()));

        return builder;
    }
}
