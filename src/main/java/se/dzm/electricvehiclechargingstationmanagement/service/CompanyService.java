package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.filter.CompanyFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService extends BaseService<CompanyFilter,CompanyModel, Long> , LogicalDeletedService<Long> {

    Page<CompanyModel> findAllByParentId(Long parentId, Pageable pageable);
}
