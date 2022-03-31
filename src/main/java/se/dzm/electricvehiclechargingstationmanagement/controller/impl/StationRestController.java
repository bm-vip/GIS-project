package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;

import java.util.List;

/**
 * Created by Behrooz.Mohamadi on 25/03/2022.
 */
@RestController
@Tag(name = "Station Rest Service v1")
@RequestMapping(value = "/api/v1/station")
public class StationRestController extends BaseRestControllerImpl<StationModel, Long> {

    private StationService stationService;

    public StationRestController(StationService service) {
        super(service, StationModel.class);
        this.stationService = service;
    }

    @GetMapping(value = {"/findAllByLocation/{latitude}/{longitude}"})
    ResponseEntity<List<StationModel>> findAllByLocation(
            @PathVariable("latitude") double latitude
            , @PathVariable("longitude") double longitude
            , @RequestParam(value = "companyId", required = false) Long companyId
    ) {
        return ResponseEntity.ok(stationService.findAllByLocation(companyId, latitude, longitude));
    }
}
