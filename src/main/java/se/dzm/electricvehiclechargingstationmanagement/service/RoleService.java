package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.filter.RoleFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;

public interface RoleService extends BaseService<RoleFilter,RoleModel, Long> {
    RoleModel findByRole(String role);
}
