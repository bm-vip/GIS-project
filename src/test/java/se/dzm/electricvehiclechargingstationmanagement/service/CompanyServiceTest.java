package se.dzm.electricvehiclechargingstationmanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTest {

    @MockBean
    CompanyRepository companyRepository;
    @SpyBean
    CompanyService companyService;

    @Test
    public void findAllByParentId_passCompanyId1ThenShouldReturn2Companies() {
        //mock db
        List<CompanyModel> companyModels = new ArrayList<>();
        companyModels.add(new CompanyModel(){{setId(1L);setParent(new CompanyModel(){{setId(1L);}});setName("company B");}});
        companyModels.add(new CompanyModel(){{setId(2L);setParent(new CompanyModel(){{setId(1L);}});setName("company C");}});
        when(companyService.findAllByParentId(1L,any(Pageable.class))).thenReturn(new PageImpl<>(companyModels));

        //test findAllByParentId service
        Page<CompanyModel> companyModelPage = companyService.findAllByParentId(1L, PageRequest.of(0, 10));
        assertThat(companyModelPage).isNotNull().isNotEmpty().size().isEqualTo(2);
        assertThat(companyModelPage.map(m->m.getName())).contains("company B","company C");
    }
}
