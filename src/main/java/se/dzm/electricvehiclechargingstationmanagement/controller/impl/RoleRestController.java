package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import se.dzm.electricvehiclechargingstationmanagement.filter.RoleFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.RoleModel;
import se.dzm.electricvehiclechargingstationmanagement.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Role Rest Service v1")
@RequestMapping(value = "/api/v1/role")
public class RoleRestController extends BaseRestControllerImpl<RoleFilter, RoleModel, Long> {

    private RoleService roleService;

    public RoleRestController(RoleService service) {
        super(service, RoleFilter.class);
        this.roleService = service;
    }
}
