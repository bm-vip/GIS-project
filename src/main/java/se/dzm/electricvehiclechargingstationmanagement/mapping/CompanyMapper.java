package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.*;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {CompanyMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CompanyMapper extends BaseMapper<CompanyModel, CompanyEntity> {

    @Override
    @Mappings({
            @Mapping(target = "parent.stationList", ignore = true),
            @Mapping(target = "stationList", qualifiedByName = "stationEntityToStationModel")
    })
    CompanyModel toModel(final CompanyEntity entity);

    @Named("stationEntityToStationModel")
    @Mapping(target = "company", ignore = true)
    StationModel stationEntityToStationModel(StationEntity entity);
}
