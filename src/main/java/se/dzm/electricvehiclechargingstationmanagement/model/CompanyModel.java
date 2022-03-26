package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class CompanyModel extends BaseModel<Long> {

    private Long id;
    private String name;
    private CompanyModel parent;
    private List<StationModel> stationList;
}
