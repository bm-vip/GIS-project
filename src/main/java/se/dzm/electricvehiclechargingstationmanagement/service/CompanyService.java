package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;

public interface CompanyService extends BaseService<CompanyModel, Long> {

    CompanyModel findByParentId(Long parentId);
}
