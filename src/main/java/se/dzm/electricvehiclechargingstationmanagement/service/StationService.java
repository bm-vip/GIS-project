package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

import java.util.List;

public interface StationService extends BaseService<StationModel, Long> , LogicalDeletedService<StationEntity,Long> {
    List<StationModel> findAllByLocation(Long companyId, double latitude, double longitude);
}
