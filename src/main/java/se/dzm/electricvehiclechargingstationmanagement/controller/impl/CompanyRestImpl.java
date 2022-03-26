package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.dzm.electricvehiclechargingstationmanagement.model.CompanyModel;
import se.dzm.electricvehiclechargingstationmanagement.service.CompanyService;

/**
 * Created by Behrooz.Mohamadi on 25/03/2022.
 */
@RestController
@Tag(name = "Company Rest Service v1")
@RequestMapping(value = "/api/v1/company")
public class CompanyRestImpl extends BaseRestImpl<CompanyModel, Long> {

    private CompanyService service;

    public CompanyRestImpl(CompanyService service) {
        super(service, CompanyModel.class);
        this.service = service;
    }

    @GetMapping(value = {"/findByParentId/{parentId}"})
    ResponseEntity<CompanyModel> findByParentId(@PathVariable("parentId") Long parentId) {
        return ResponseEntity.ok(service.findByParentId(parentId));
    }
}
