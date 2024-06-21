package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import org.locationtech.jts.geom.Point;

public interface StationService extends BaseService<StationModel, Long> , LogicalDeletedService<Long> {
    Page<StationModel> findClosestByHaversineFormula(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);
    Page<StationModel> findClosestByGeoPoint(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);
}
