package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.QStationEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.enums.DistanceType;
import se.dzm.electricvehiclechargingstationmanagement.mapping.StationMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.StationRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static se.dzm.electricvehiclechargingstationmanagement.util.DistanceUtil.distance;
import static se.dzm.electricvehiclechargingstationmanagement.util.MapperHelper.option;

@Service
public class StationServiceImpl extends BaseServiceImpl<StationModel, StationEntity, Long> implements StationService {

    private StationRepository stationRepository;
    private StationMapper stationMapper;

    @Autowired
    public StationServiceImpl(StationRepository repository, StationMapper mapper) {
        super(repository, mapper);
        this.stationRepository = repository;
        this.stationMapper = mapper;
    }

    @Override
    public Predicate queryBuilder(StationModel filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QStationEntity path = QStationEntity.stationEntity;

        option(() -> filter.getId())
                .ifPresent(id -> builder.and(path.id.eq(id)));
        option(() -> filter.getLatitude())
                .ifPresent(latitude -> builder.and(path.latitude.eq(latitude)));
        option(() -> filter.getLongitude())
                .ifPresent(longitude -> builder.and(path.longitude.eq(longitude)));
        option(() -> filter.getCompany().getId())
                .ifPresent(companyId -> builder.and(path.company.id.eq(companyId)));
        if (StringUtils.hasText(filter.getName()))
            builder.and(path.name.contains(filter.getName()));

        return builder;
    }

    @Override
    public List<StationModel> findAllByLocation(Long companyId, double latitude, double longitude) {
        StationModel filter = new StationModel(){{setCompany(new CompanyModel(){{setId(companyId);}});}};
        return StreamSupport.stream(stationRepository.findAll(queryBuilder(filter)).spliterator(), false)
                .map(entity -> {
                    StationModel model = stationMapper.toModel(entity);
                    model.setDistance(distance(latitude, longitude, model.getLatitude(), model.getLongitude(), DistanceType.Kilometers));
                    return model;
                })
                .sorted((o1, o2) -> o1.getDistance().compareTo(o2.getDistance()))
                .collect(Collectors.toList());
    }
}
