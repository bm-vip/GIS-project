package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
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

    public StationModel(long id, Date createdDate, Date modifiedDate, String name, Double latitude, Double longitude, Double distance, Long companyId,Date companyCreatedDate, Date companyModifiedDate, String companyName) {
        setId(id);
        super.createdDate = createdDate;
        super.modifiedDate = modifiedDate;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.company = new CompanyModel(companyId, companyCreatedDate,companyModifiedDate,companyName);
    }
}
