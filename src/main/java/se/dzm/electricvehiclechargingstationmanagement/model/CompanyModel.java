package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class CompanyModel extends BaseModel<Long> {

    @NotNull
    @NotBlank
    private String name;
    private CompanyModel parent;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<StationModel> stationList;
}
