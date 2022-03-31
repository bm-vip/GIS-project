package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import se.dzm.electricvehiclechargingstationmanagement.entity.RoleEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper extends BaseMapper<RoleModel, RoleEntity> {
}
