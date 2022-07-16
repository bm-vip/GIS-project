package se.dzm.electricvehiclechargingstationmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Created by Behrooz.Mohamadi on 24/03/2022.
 */
@Data
@Entity
@Table(name = "tbl_company")
@Audited
public class CompanyEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_company", sequenceName = "seq_company", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_company")
    private Long id;

    @NotNull
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "parent_company_id")
    private CompanyEntity parent;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    private List<StationEntity> stationList;

    @Override
    public String getSelectTitle() {
        return name;
    }
}
