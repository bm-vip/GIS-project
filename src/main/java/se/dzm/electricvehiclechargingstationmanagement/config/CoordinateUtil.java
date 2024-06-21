package se.dzm.electricvehiclechargingstationmanagement.config;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class CoordinateUtil {
    private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    public static Point fromLatLong(double latitude, double longitude) {
        var point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        //point.setSRID(4326); in geometryFactory has been added
        return point;
    }
}
