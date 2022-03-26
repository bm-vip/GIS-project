package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;

@Repository
public interface StationRepository extends BaseRepository<StationEntity, Long> {
}