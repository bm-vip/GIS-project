package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CompanyModel extends BaseModel<Long> {

    @NotNull
    @NotBlank
    private String name;
    private CompanyModel parent;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<StationModel> stationList;

    public CompanyModel(long id, Date createdDate, Date modifiedDate, String name) {
        setId(id);
        super.createdDate = createdDate;
        super.modifiedDate = modifiedDate;
        this.name = name;
    }
}
