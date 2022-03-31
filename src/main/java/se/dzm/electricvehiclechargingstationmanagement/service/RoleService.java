package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;

public interface RoleService extends BaseService<RoleModel, Long> {
    RoleModel findByRole(String role);
}
