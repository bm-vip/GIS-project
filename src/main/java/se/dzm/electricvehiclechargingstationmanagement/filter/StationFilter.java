package se.dzm.electricvehiclechargingstationmanagement.filter;

import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

@Setter
@ToString
public class StationFilter {
    private Long id;
    private String name;
    @Min(value = -90)
    @Max(value = 90)
    private Double latitude;
    @Min(value = -180)
    @Max(value = 180)
    private Double longitude;
    private Long companyId;
    private Double distance;

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getName() {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(name);
    }

    public Optional<Double> getLatitude() {
        return Optional.ofNullable(latitude);
    }

    public Optional<Double> getLongitude() {
        return Optional.ofNullable(longitude);
    }

    public Optional<Long> getCompanyId() {
        return Optional.ofNullable(companyId);
    }

    public Optional<Double> getDistance() {
        return Optional.ofNullable(distance);
    }
}
