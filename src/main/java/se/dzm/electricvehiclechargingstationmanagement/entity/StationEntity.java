package se.dzm.electricvehiclechargingstationmanagement.entity;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
@Entity
@Table(name = "tbl_station")
@Audited
public class StationEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_station", sequenceName = "seq_station", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_station")
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @Column(name = "lat", nullable = false)
    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    private Double latitude;

    @Column(name = "lng", nullable = false)
    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;



    @Override
    public String getSelectTitle() {
        return name;
    }
}
