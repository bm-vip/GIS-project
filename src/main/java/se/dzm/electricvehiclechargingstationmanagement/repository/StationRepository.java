package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;

import java.util.List;

@Repository
public interface StationRepository extends BaseRepository<StationEntity, Long> {
    List<StationEntity> findAllByCompanyId(long companyId);

    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *" +
            " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";
    @Query("SELECT s FROM StationEntity s WHERE s.company.id = :companyId ORDER BY " + HAVERSINE_FORMULA)
    Page<StationEntity> findAllByLocationAndDistance(Long companyId, double latitude, double longitude, Pageable pageable);
}