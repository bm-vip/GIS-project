package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class CompanyController extends BaseRestImpl<CompanyModel, Long> {

    private CompanyService companyService;

    public CompanyController(CompanyService service) {
        super(service, CompanyModel.class);
        this.companyService = service;
    }

    @GetMapping(value = {"/findAllByParentId/{parentId}"})
    ResponseEntity<Page<CompanyModel>> findByParentId(@PathVariable("parentId") Long parentId
            , @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(companyService.findAllByParentId(parentId, pageable));
    }
}
