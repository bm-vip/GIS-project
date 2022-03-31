package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.RoleEntity;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(String role);
}
