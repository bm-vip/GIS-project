package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class CompanyModel extends BaseModel<Long> {

    @NotEmpty
    private String name;
    private CompanyModel parent;
    private List<StationModel> stationList;
}
