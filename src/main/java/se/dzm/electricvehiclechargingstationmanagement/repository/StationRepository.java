package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;

import java.util.List;

@Repository
public interface StationRepository extends BaseRepository<StationEntity, Long> {
    List<StationEntity> findAllByCompanyId(long companyId);
}