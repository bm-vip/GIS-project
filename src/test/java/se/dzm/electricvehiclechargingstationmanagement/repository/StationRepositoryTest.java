package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Test
    @Order(1)
    @Commit
    public void save_shouldSaveStationEntityToDatabase() {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setName("test station");
        stationEntity.setLatitude(59.409801);
        stationEntity.setLongitude(17.827791);
        stationEntity.setCompany(new CompanyEntity(){{setId(3L);}});
        stationRepository.save(stationEntity);

        Assertions.assertThat(stationEntity.getId()).isEqualTo(18);
        Assertions.assertThat(stationEntity.getCompany().getId()).isEqualTo(3L);
    }

    @Test
    @Order(2)
    @Commit
    public void deleteById_shouldDeleteByIdFromDatabase(){
        stationRepository.deleteById(18L);
        Optional<StationEntity> optionalStation = stationRepository.findById(18L);

        Assertions.assertThat(optionalStation).isEmpty();
    }

    @Test
    public void update_shouldUpdateStationEntityToDatabase(){
        StationEntity stationEntity = stationRepository.findById(1L).get();
        stationEntity.setName("new Station test");
        StationEntity stationUpdated = stationRepository.save(stationEntity);

        Assertions.assertThat(stationUpdated.getName()).isEqualTo("new Station test");
    }

    @Test
    public void findById_shouldReturnStationEntity() {
        Optional<StationEntity> optional = stationRepository.findById(1L);

        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getName()).isEqualTo("station A1");
    }

    @Test
    public void findAll_shouldReturnPageableStationEntities() {
        Page<StationEntity> page = stationRepository.findAll(Pageable.ofSize(10));

        Assertions.assertThat(page).isNotEmpty().size().isEqualTo(10);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(17);
    }
}
