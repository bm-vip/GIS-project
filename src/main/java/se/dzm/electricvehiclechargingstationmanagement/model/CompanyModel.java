package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class CompanyModel extends BaseModel<Long> {

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    private CompanyModel parent;
    @Valid
    private List<StationModel> stationList;
}
