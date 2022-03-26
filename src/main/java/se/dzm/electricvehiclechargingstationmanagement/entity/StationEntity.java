package se.dzm.electricvehiclechargingstationmanagement.entity;

import lombok.Data;

import javax.persistence.*;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
@Entity
@Table(name = "tbl_station")
public class StationEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_STATION", sequenceName = "SEQ_STATION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STATION")
    private Long id;

    private String name;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lng")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;



    @Override
    public String getSelectTitle() {
        return name;
    }
}
