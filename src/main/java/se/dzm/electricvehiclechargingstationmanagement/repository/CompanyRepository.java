package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;

import java.util.Optional;

@Repository
public interface CompanyRepository extends BaseRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findByParentId(Long parentId);
}