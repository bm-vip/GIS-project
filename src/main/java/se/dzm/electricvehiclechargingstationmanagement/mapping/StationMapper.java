package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.*;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {CompanyMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StationMapper extends BaseMapper<StationModel, StationEntity> {

    @Override
    @Mapping(target = "company.stationList", expression = "java(null)")
    StationModel toModel(final StationEntity entity);
}
