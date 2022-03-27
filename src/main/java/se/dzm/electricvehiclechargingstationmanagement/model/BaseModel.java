package se.dzm.electricvehiclechargingstationmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseModel<ID extends Serializable> implements Serializable {
    private ID id;
    protected int version;
    @JsonIgnore
    private String selectTitle;
}
