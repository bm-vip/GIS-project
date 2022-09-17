package se.dzm.electricvehiclechargingstationmanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
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
    public void findClosest_passCompanyId1ThenShouldReturn10Station() {
        //mock db
        List<StationEntity> stationEntities = new ArrayList<>();
        stationEntities.add(new StationEntity(){{setName("station A1");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.700568);setLongitude(51.349122);}});
        stationEntities.add(new StationEntity(){{setName("station A2");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.657532);setLongitude(51.050988);}});
        stationEntities.add(new StationEntity(){{setName("station A3");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.819692);setLongitude(50.929555);}});
        stationEntities.add(new StationEntity(){{setName("station A4");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(35.370555);setLongitude(51.877604);}});
        stationEntities.add(new StationEntity(){{setName("station A6");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(32.071811);setLongitude(54.203648);}});
        stationEntities.add(new StationEntity(){{setName("station A9");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(31.594328);setLongitude(49.123338);}});
        stationEntities.add(new StationEntity(){{setName("station A10");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(38.390648);setLongitude(45.165619);}});
        stationEntities.add(new StationEntity(){{setName("station A5");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(32.758118);setLongitude(58.623101);}});
        stationEntities.add(new StationEntity(){{setName("station A8");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(29.014746);setLongitude(53.212981);}});
        stationEntities.add(new StationEntity(){{setName("station A7");setCompany(new CompanyEntity(){{setId(1L);}});setLatitude(26.801396);setLongitude(58.885711);}});
        when(stationRepository.findAllByLocationAndDistance(1L,35.700568, 51.349122,10000D,PageRequest.of(0,10))).thenReturn(new PageImpl<>(stationEntities));

        //test findClosest service
        Page<StationModel> stationModelPage = stationService.findClosest(1L, 35.700568, 51.349122, PageRequest.of(0,10));
        assertThat(stationModelPage).isNotNull().isNotEmpty().size().isEqualTo(10);
        assertThat(stationModelPage.getTotalElements()).isEqualTo(10);
        List<String> expected = List.of("station A1","station A2","station A3","station A4","station A6","station A9","station A10","station A5","station A8","station A7");
        List<String> actual = stationModelPage.stream().map(StationModel::getName).collect(Collectors.toList());
        assertThat(actual).isEqualTo(expected);
    }
}
