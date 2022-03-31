package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.springframework.stereotype.Repository;
import se.dzm.electricvehiclechargingstationmanagement.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
	 Optional<UserEntity> findByUserName(String userName);
}
