package se.dzm.electricvehiclechargingstationmanagement.model;

import lombok.Data;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
public class StationModel extends BaseModel<Long> {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private CompanyModel company;
}
