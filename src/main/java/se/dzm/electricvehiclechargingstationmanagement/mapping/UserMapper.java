package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import se.dzm.electricvehiclechargingstationmanagement.entity.UserEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.UserModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {RoleMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper extends BaseMapper<UserModel, UserEntity> {
}
