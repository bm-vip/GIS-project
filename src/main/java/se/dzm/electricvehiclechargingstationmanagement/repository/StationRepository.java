package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;

import java.util.List;

@Repository
public interface StationRepository extends BaseRepository<StationEntity, Long> {
    List<StationEntity> findAllByCompanyId(long companyId);

    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude)))) ";
    @Query("SELECT new se.dzm.electricvehiclechargingstationmanagement.model.StationModel(s.id, s.createdDate, s.modifiedDate, s.name, s.latitude, s.longitude, " + HAVERSINE_FORMULA + ", c.id, c.createdDate, c.modifiedDate, c.name) " +
            "FROM StationEntity s JOIN s.company c " +
            "WHERE (:companyId is null or c.id = :companyId) AND " + HAVERSINE_FORMULA + " < :maxDistance " +
            "ORDER BY " + HAVERSINE_FORMULA)
    Page<StationModel> findAllByLocationAndDistance(Long companyId, double latitude, double longitude, double maxDistance, Pageable pageable);

}