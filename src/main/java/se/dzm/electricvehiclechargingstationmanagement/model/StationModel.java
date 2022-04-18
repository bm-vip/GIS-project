package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class StationModel extends BaseModel<Long> {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    private Double latitude;
    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    private Double longitude;
    @NotNull
    private CompanyModel company;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double distance;
}
