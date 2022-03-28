package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.*;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {CompanyMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StationMapper extends BaseMapper<StationModel, StationEntity> {

    @Override
    @Mappings({
            @Mapping(target = "company.stationList", ignore = true),
            @Mapping(target = "company.parent", ignore = true)
    })
    StationModel toModel(final StationEntity entity);
}
