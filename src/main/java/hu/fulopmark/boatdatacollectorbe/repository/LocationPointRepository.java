package hu.fulopmark.boatdatacollectorbe.repository;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface LocationPointRepository extends JpaRepository<LocationPoint, Long> {

    List<LocationPoint> findAllByTimestampBetweenOrderByTimestampDesc(Instant start, Instant end);
}
