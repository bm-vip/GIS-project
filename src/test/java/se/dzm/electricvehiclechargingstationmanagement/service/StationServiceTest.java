package se.dzm.electricvehiclechargingstationmanagement.service;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.QStationEntity;
import se.dzm.electricvehiclechargingstationmanagement.entity.StationEntity;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StationServiceTest {

    @MockBean
    StationRepository stationRepository;
    @SpyBean
    StationService stationService;

    @Test
    public void findAllByLocation_passCompanyId1ThenShouldReturn10Station() {
        //mock db
        List<StationEntity> stationEntities = new ArrayList<>();
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.700568);setLongitude(51.349122);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.657532);setLongitude(51.050988);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.819692);setLongitude(50.929555);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.370555);setLongitude(51.877604);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(32.758118);setLongitude(58.623101);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(32.071811);setLongitude(54.203648);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(26.801396);setLongitude(58.885711);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(29.014746);setLongitude(53.212981);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(31.594328);setLongitude(49.123338);}});
        stationEntities.add(new StationEntity(){{setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(38.390648);setLongitude(45.165619);}});
        when(stationRepository.findAll(new BooleanBuilder(QStationEntity.stationEntity.company.id.eq(1L)))).thenReturn(stationEntities);

        //test findAllByLocation service
        List<StationModel> stationModels = stationService.findAllByLocation(1L, 35.294952, 53.715041);
        assertThat(stationModels).isNotNull().isNotEmpty().size().isEqualTo(10);
        List<Double> expected = List.of(2.42, 3.17, 3.54, 3.74, 5.23, 7.71, 8.57, 10.13, 12.08, 15.41);
        List<Double> actual = stationModels.stream().map(StationModel::getDistance).collect(Collectors.toList());
        assertThat(actual).isEqualTo(expected);
    }
}
