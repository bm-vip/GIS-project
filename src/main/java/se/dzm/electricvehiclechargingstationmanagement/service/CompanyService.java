package se.dzm.electricvehiclechargingstationmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;

public interface CompanyService extends BaseService<CompanyModel, Long> {

    Page<CompanyModel> findByParentId(Long parentId, Pageable pageable);
}
