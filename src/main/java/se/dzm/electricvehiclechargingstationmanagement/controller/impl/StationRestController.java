package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import se.dzm.electricvehiclechargingstationmanagement.controller.LogicalDeletedRestController;
import se.dzm.electricvehiclechargingstationmanagement.filter.StationFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.service.LogicalDeletedService;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@RestController
@Tag(name = "Station Rest Service v1")
@RequestMapping(value = "/api/v1/station")
@Validated
public class StationRestController extends BaseRestControllerImpl<StationFilter, StationModel, Long> implements LogicalDeletedRestController<Long> {

    private StationService stationService;

    public StationRestController(StationService service) {
        super(service, StationFilter.class);
        this.stationService = service;
    }

    @GetMapping(value = {"/findClosest-by-haversine"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Page<StationModel>> findClosestByHaversineFormula(
            @RequestParam @Min(value = -90) @Max(value = 90) double latitude,
            @RequestParam @Min(value = -180) @Max(value = 180) double longitude,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false, defaultValue = "100") Double maxDistance,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(stationService.findClosestByHaversineFormula(companyId, latitude, longitude, maxDistance, PageRequest.of(page, size)));
    }
    @GetMapping(value = {"/findClosest-by-geopoint"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Page<StationModel>> findClosestByGeoPoint(
            @RequestParam @Min(value = -90) @Max(value = 90) double latitude,
            @RequestParam @Min(value = -180) @Max(value = 180) double longitude,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false, defaultValue = "100") Double maxDistance,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(stationService.findClosestByGeoPoint(companyId, latitude, longitude, maxDistance, PageRequest.of(page, size)));
    }

    @Override
    public LogicalDeletedService<Long> getService() {
        return stationService;
    }
}
