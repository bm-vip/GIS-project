package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel<ID extends Serializable> implements Serializable {
    private ID id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Date modifiedDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Date createdDate;;
    protected int version;
    private String selectTitle;
}
