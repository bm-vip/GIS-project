package se.dzm.electricvehiclechargingstationmanagement.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.dzm.electricvehiclechargingstationmanagement.model.StationModel;
import se.dzm.electricvehiclechargingstationmanagement.service.StationService;

/**
 * Created by Behrooz.Mohamadi on 25/03/2022.
 */
@RestController
@Tag(name = "Station Rest Service v1")
@RequestMapping(value = "/api/v1/station")
public class StationRestImpl extends BaseRestImpl<StationModel, Long> {

    public StationRestImpl(StationService service) {
        super(service, StationModel.class);
    }
}
