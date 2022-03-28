package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class StationModel extends BaseModel<Long> {

    @NotEmpty
    private String name;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private CompanyModel company;
    private Double distance;
}
