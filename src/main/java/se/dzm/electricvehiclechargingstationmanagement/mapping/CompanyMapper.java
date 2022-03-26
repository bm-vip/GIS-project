package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.*;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {CompanyMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CompanyMapper extends BaseMapper<CompanyModel, CompanyEntity> {

    @Override
    @Mapping(target = "parent.stationList", expression = "java(null)")
    CompanyModel toModel(final CompanyEntity entity);
}
