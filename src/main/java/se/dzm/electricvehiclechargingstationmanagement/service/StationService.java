package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.filter.StationFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StationService extends BaseService<StationFilter,StationModel, Long> , LogicalDeletedService<Long> {
    Page<StationModel> findClosestByHaversineFormula(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);
    Page<StationModel> findClosestByGeoPoint(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);
}
