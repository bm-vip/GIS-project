package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.dzm.electricvehiclechargingstationmanagement.controller.LogicalDeletedRestController;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.service.LogicalDeletedService;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Behrooz.Mohamadi on 25/03/2022.
 */
@RestController
@Tag(name = "Station Rest Service v1")
@RequestMapping(value = "/api/v1/station")
@Validated
public class StationRestController extends BaseRestControllerImpl<StationModel, Long> implements LogicalDeletedRestController<Long> {

    private StationService stationService;

    public StationRestController(StationService service) {
        super(service, StationModel.class);
        this.stationService = service;
    }

    @GetMapping(value = {"/findClosest"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Page<StationModel>> findClosest(
            @RequestParam("latitude") @Min(value = -90) @Max(value = 90) double latitude,
            @RequestParam("longitude") @Min(value = -180) @Max(value = 180) double longitude,
            @RequestParam(value = "companyId", required = false) Long companyId,
            @RequestParam(value = "distance", required = false, defaultValue = "1000") Double maxDistance,
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(stationService.findClosest(companyId, latitude, longitude, maxDistance, PageRequest.of(page, size)));
    }

    @Override
    public LogicalDeletedService<Long> getService() {
        return stationService;
    }
}
