package se.dzm.electricvehiclechargingstationmanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
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
        List<StationModel> stationModels = new ArrayList<>();
        stationModels.add(new StationModel().setName("station A1").setLatitude(35.700568).setLongitude(51.349122).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A2").setLatitude(35.657532).setLongitude(51.050988).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A3").setLatitude(35.819692).setLongitude(50.929555).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A4").setLatitude(35.370555).setLongitude(51.877604).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A6").setLatitude(32.071811).setLongitude(54.203648).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A9").setLatitude(31.594328).setLongitude(49.123338).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A10").setLatitude(38.390648).setLongitude(45.165619).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A5").setLatitude(32.758118).setLongitude(58.623101).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A8").setLatitude(29.014746).setLongitude(53.212981).setCompany(new CompanyModel(){{setId(1L);}}));
        stationModels.add(new StationModel().setName("station A7").setLatitude(26.801396).setLongitude(58.885711).setCompany(new CompanyModel(){{setId(1L);}}));
        when(stationRepository.findAllByHaversineFormula(1L,35.700568, 51.349122,10D, PageRequest.of(0,10))).thenReturn(new PageImpl<>(stationModels));

        //test findClosest service
        Page<StationModel> stationModelPage = stationService.findClosestByHaversineFormula(1L, 35.700568, 51.349122,10D, PageRequest.of(0,10));
        assertThat(stationModelPage).isNotNull().isNotEmpty().size().isEqualTo(10);
        assertThat(stationModelPage.getTotalElements()).isEqualTo(10);
        List<String> expected = List.of("station A1","station A2","station A3","station A4","station A6","station A9","station A10","station A5","station A8","station A7");
        List<String> actual = stationModelPage.stream().map(StationModel::getName).collect(Collectors.toList());
        assertThat(actual).isEqualTo(expected);
    }
}
