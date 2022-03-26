package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseModel<ID extends Serializable> implements Serializable {
    protected int version;
    @JsonIgnore
    private String selectTitle;

    public abstract ID getId();
    public abstract void setId(ID id);
}
