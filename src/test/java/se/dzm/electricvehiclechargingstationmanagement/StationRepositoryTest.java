package se.dzm.electricvehiclechargingstationmanagement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;
import se.dzm.electricvehiclechargingstationmanagement.repository.StationRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Order(1)
    @Commit
    public void saveStationTest() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("test company");
        companyRepository.save(companyEntity);

        StationEntity stationEntity = new StationEntity();
        stationEntity.setName("test station");
        stationEntity.setLatitude(59.409801);
        stationEntity.setLatitude(17.827791);
        stationEntity.setCompany(companyEntity);
        stationRepository.save(stationEntity);

        Assertions.assertThat(companyEntity.getId()).isGreaterThan(0);
        Assertions.assertThat(stationEntity.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findStationByIdTest() {
        Optional<StationEntity> optional = stationRepository.findById(1L);

        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getName()).isEqualTo("station A1");
    }

    @Test
    @Order(3)
    public void findAllStationTest() {
        List<StationEntity> all = stationRepository.findAll();

        Assertions.assertThat(all).isNotEmpty().size().isEqualTo(18);
    }

    @Test
    @Order(4)
    @Commit
    public void updateStationTest(){
        StationEntity StationEntity = stationRepository.findById(1L).get();
        StationEntity.setName("new Station test");
        StationEntity StationUpdated = stationRepository.save(StationEntity);

        Assertions.assertThat(StationUpdated.getName()).isEqualTo("new Station test");
    }

    @Test
    @Order(5)
    @Commit
    public void deleteStationTest(){
        stationRepository.deleteById(1L);
        Optional<StationEntity> optionalStation = stationRepository.findById(1L);

        Assertions.assertThat(optionalStation).isEmpty();
    }
}
