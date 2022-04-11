package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel<ID extends Serializable> implements Serializable {
    private ID id;
    protected Date modifiedDate;
    protected Date createdDate;;
    protected int version;
    @JsonIgnore
    private String selectTitle;

    public void setModifiedDate(Date modifiedDate) {
        //notting
    }

    public void setCreatedDate(Date createdDate) {
        //notting
    }
}
