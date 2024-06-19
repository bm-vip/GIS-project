package se.dzm.electricvehiclechargingstationmanagement.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.dzm.electricvehiclechargingstationmanagement.entity.QStationEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.mapping.StationMapper;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.StationRepository;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;

import static se.dzm.electricvehiclechargingstationmanagement.util.MapperHelper.option;

@Service
public class StationServiceImpl extends BaseServiceImpl<StationModel, StationEntity, Long> implements StationService {

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
    public Page<StationModel> findClosest(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable) {
        return stationRepository.findAllByLocationAndDistance(companyId,latitude, longitude, maxDistance, pageable);
    }
}
