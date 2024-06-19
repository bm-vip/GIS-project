package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

public interface StationService extends BaseService<StationModel, Long> , LogicalDeletedService<Long> {
    Page<StationModel> findClosest(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);
}
