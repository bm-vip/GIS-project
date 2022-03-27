package se.dzm.electricvehiclechargingstationmanagement;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Order(1)
    @Commit
    public void saveCompanyTest() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("test company");
        companyRepository.save(companyEntity);

        CompanyEntity childEntity = new CompanyEntity();
        childEntity.setName("test child company");
        childEntity.setParent(companyEntity);
        companyRepository.save(childEntity);

        Assertions.assertThat(companyEntity.getId()).isGreaterThan(0);
        Assertions.assertThat(childEntity.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findCompanyByIdTest() {
        Optional<CompanyEntity> optional = companyRepository.findById(1L);

        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getName()).isEqualTo("company A");
    }

    @Test
    @Order(3)
    public void findAllCompanyTest() {
        List<CompanyEntity> all = companyRepository.findAll();

        Assertions.assertThat(all).isNotEmpty().size().isEqualTo(5);
    }
    @Test
    @Order(4)
    public void findCompanyByParentIdTest() {
        Page<CompanyEntity> companyEntityPage = companyRepository.findByParentId(4L, Pageable.ofSize(10));

        Assertions.assertThat(companyEntityPage.getTotalElements()).isEqualTo(1);
        Assertions.assertThat(companyEntityPage.getContent().get(0).getName()).isEqualTo("test child company");
    }

    @Test
    @Order(5)
    @Commit
    public void updateCompanyTest(){
        CompanyEntity companyEntity = companyRepository.findById(4L).get();
        companyEntity.setName("new company test");
        CompanyEntity companyUpdated = companyRepository.save(companyEntity);

        Assertions.assertThat(companyUpdated.getName()).isEqualTo("new company test");
    }

    @Test
    @Order(6)
    @Commit
    public void deleteCompanyTest(){
        companyRepository.deleteById(5L);
        Optional<CompanyEntity> optional = companyRepository.findById(5L);

        Assertions.assertThat(optional).isEmpty();
    }
}
