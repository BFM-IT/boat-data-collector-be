package hu.fulopmark.boatdatacollectorbe.repository;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationPointRepository extends JpaRepository<LocationPoint, Long> {
}
