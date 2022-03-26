package se.dzm.electricvehiclechargingstationmanagement.util;

import se.dzm.electricvehiclechargingstationmanagement.enums.DistanceType;

public class DistanceUtil {

    public static double distance(double lat1, double lon1, double lat2, double lon2, DistanceType unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        dist = switch (unit) {
            case Miles -> dist * 60 * 1.1515;
            case Kilometers -> dist * 1.609344;
            case Nautical_Miles -> dist * 0.8684;
        };
        return (dist);
    }

    /*
     * This function converts decimal degrees to radians
     * */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*
     * This function converts radians to decimal degrees
     * */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
