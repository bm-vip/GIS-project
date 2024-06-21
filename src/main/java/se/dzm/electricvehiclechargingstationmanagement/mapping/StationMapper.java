package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.*;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {CompanyMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StationMapper extends BaseMapper<StationModel, StationEntity> {

    @Override
    @Mappings({
            @Mapping(target = "company.stationList", ignore = true),
            @Mapping(target = "company.parent", ignore = true)
    })
    StationModel toModel(final StationEntity entity);

    @Mapping(target = "location", expression = "java(se.dzm.electricvehiclechargingstationmanagement.config.CoordinateUtil.fromLatLong(model.getLatitude(), model.getLongitude()))")
    StationEntity toEntity(StationModel model);

}
