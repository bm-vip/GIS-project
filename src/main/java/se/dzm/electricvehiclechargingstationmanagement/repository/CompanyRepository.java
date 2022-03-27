package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;

import java.util.Optional;

@Repository
public interface CompanyRepository extends BaseRepository<CompanyEntity, Long> {

    Page<CompanyEntity> findByParentId(Long parentId, Pageable pageable);
}