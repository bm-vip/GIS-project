package se.dzm.electricvehiclechargingstationmanagement.filter;

import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;


@Setter
@ToString
public class CompanyFilter implements Serializable {
    private Long id;
    private String name;
    private Long parentId;

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getName() {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(name);
    }

    public Optional<Long> getParentId() {
        return Optional.ofNullable(parentId);
    }
}
