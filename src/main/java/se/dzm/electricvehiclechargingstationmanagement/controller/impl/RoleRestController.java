package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;

/**
 * Created by Behrooz.Mohamadi on 29/03/2022.
 */
@RestController
@Tag(name = "Role Rest Service v1")
@RequestMapping(value = "/api/v1/role")
public class RoleRestController extends BaseRestControllerImpl<RoleModel, Long> {

    private RoleService roleService;

    public RoleRestController(RoleService service) {
        super(service, RoleModel.class);
        this.roleService = service;
    }
}
