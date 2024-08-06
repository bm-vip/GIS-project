package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import se.dzm.electricvehiclechargingstationmanagement.config.CoordinateUtil;
import se.dzm.electricvehiclechargingstationmanagement.entity.QStationEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.filter.StationFilter;
import se.dzm.electricvehiclechargingstationmanagement.mapping.StationMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.StationRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends BaseServiceImpl<StationFilter,StationModel, StationEntity, Long> implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository repository, StationMapper mapper) {
        super(repository, mapper);
        this.stationRepository = repository;
    }

    @Override
    public JpaRepository<StationEntity,Long> getRepository() {
        return stationRepository;
    }

    @Override
    public Predicate queryBuilder(StationFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        QStationEntity path = QStationEntity.stationEntity;

        filter.getId().ifPresent(value -> builder.and(path.id.eq(value)));
        filter.getLatitude().ifPresent(value -> builder.and(path.latitude.eq(value)));
        filter.getLongitude().ifPresent(value -> builder.and(path.longitude.eq(value)));
        filter.getName().ifPresent(value -> builder.and(path.name.contains(value)));
        filter.getCompanyId().ifPresent(value -> builder.and(path.company.id.eq(value)));

        return builder;
    }

    @Override
    public Page<StationModel> findClosestByHaversineFormula(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable) {
        return stationRepository.findAllByHaversineFormula(companyId,latitude, longitude, maxDistance, pageable);
    }
    @Override
    public Page<StationModel> findClosestByGeoPoint(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable) {
        var location = CoordinateUtil.fromLatLong(longitude, latitude);
        return stationRepository.findAllByGeoPoint(companyId,location, maxDistance, pageable);
    }
}
