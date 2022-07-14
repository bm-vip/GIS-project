package se.dzm.electricvehiclechargingstationmanagement.service;

import org.junit.jupiter.api.Test;
import se.dzm.electricvehiclechargingstationmanagement.enums.DistanceType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static se.dzm.electricvehiclechargingstationmanagement.util.DistanceUtil.distance;

public class DistanceUtilTest {

    @Test
    public void distance_shoudRetun_2KM() {
        assertThat(distance(5.05D, 4.05D, 5.07D, 4.05D, DistanceType.Kilometers)).isEqualTo(0.03D);
        assertThat(distance(40.689202777778D, 38.889069444444D, -74.044219444444D, -77.034502777778D, DistanceType.Kilometers)).isEqualTo(218.69D);
    }
}
