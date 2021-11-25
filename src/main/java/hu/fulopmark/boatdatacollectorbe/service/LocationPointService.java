package hu.fulopmark.boatdatacollectorbe.service;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import hu.fulopmark.boatdatacollectorbe.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static hu.fulopmark.boatdatacollectorbe.util.MathUtil.ZERO_ALTITUDE;

@Service
@Slf4j
public class LocationPointService {

    /**
     * Calculate the sum of distances between an array of 2D points.
     * Currently ignores altitude.
     *
     * @param locationPoints MUST BE ORDERED BY TIMESTAMP ASC!
     * @return Sum of distances between the specified points, in meters.
     */
    public double sumDistanceBetweenPoints(List<LocationPoint> locationPoints) {
        log.info("Calculating sumDistance by {} number of points", locationPoints.size());
        if (locationPoints.size() <= 1) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < locationPoints.size() - 1; i++) {
            LocationPoint p1 = locationPoints.get(i);
            LocationPoint p2 = locationPoints.get(i + 1);
            double distance = MathUtil.distance(p1.getLatitude(), p2.getLatitude(), p1.getLongitude(), p2.getLongitude(), ZERO_ALTITUDE, ZERO_ALTITUDE);
            log.debug("Calculated distance between ({} ; {}) and ({} ; {}) : {} meters", p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude(), distance);
            sum += distance;
        }

        log.info("sumDistance: {}", sum);
        return sum;
    }


}
