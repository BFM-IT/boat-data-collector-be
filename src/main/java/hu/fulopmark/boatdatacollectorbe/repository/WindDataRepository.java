package hu.fulopmark.boatdatacollectorbe.repository;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import hu.fulopmark.boatdatacollectorbe.entity.WindData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindDataRepository extends JpaRepository<WindData, Long> {
}
