package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class StationModel extends BaseModel<Long> {

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "latitude is mandatory")
    private Double latitude;
    @NotNull(message = "longitude is mandatory")
    private Double longitude;
    @NotNull(message = "company is mandatory")
    private CompanyModel company;
    private Double distance;
}
