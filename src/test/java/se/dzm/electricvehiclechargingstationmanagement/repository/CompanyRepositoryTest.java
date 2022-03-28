package se.dzm.electricvehiclechargingstationmanagement.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import se.dzm.electricvehiclechargingstationmanagement.entity.CompanyEntity;
import se.dzm.electricvehiclechargingstationmanagement.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Order(1)
    @Commit
    public void save_shouldSaveCompanyEntityToDatabase() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("test company");
        companyRepository.save(companyEntity);

        CompanyEntity childEntity = new CompanyEntity();
        childEntity.setName("test child company");
        childEntity.setParent(companyEntity);
        companyRepository.save(childEntity);

        Assertions.assertThat(companyEntity.getId()).isEqualTo(4);
        Assertions.assertThat(childEntity.getId()).isEqualTo(5);
    }

    @Test
    @Order(2)
    @Commit
    public void deleteById_shouldDeleteByIdFromDatabase(){
        companyRepository.deleteById(5L);
        companyRepository.deleteById(4L);
        Optional<CompanyEntity> childOptional = companyRepository.findById(5L);
        Optional<CompanyEntity> parentOptional = companyRepository.findById(4L);

        Assertions.assertThat(childOptional).isEmpty();
        Assertions.assertThat(parentOptional).isEmpty();
    }

    @Test
    public void update_shouldUpdateCompanyEntityToDatabase() {
        CompanyEntity companyEntity = companyRepository.findById(3L).get();
        companyEntity.setName("new company test");
        CompanyEntity companyUpdated = companyRepository.save(companyEntity);

        Assertions.assertThat(companyUpdated.getName()).isEqualTo("new company test");
    }

    @Test
    public void findById_shouldReturnCompanyEntity() {
        Optional<CompanyEntity> optional = companyRepository.findById(1L);

        Assertions.assertThat(optional).isPresent();
        Assertions.assertThat(optional.get().getName()).isEqualTo("company A");
    }

    @Test
    public void findAll_shouldReturnPageableCompanyEntities() {
        Page<CompanyEntity> page = companyRepository.findAll(PageRequest.ofSize(10));

        Assertions.assertThat(page).isNotEmpty().size().isEqualTo(3);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(3);
    }

    @Test
    public void countAll_shouldReturnTotalNumberOfCompanies() {
        long count = companyRepository.count();

        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    public void findAllByParentId_shouldReturnPageableCompanyEntities() {
        Page<CompanyEntity> page = companyRepository.findByParentId(1L, Pageable.ofSize(10));

        Assertions.assertThat(page).isNotEmpty().size().isEqualTo(1);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(1);
        Assertions.assertThat(page.getContent().get(0).getName()).isEqualTo("company B");
    }
}
