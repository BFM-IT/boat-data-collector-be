package hu.fulopmark.boatdatacollectorbe.repository;

import hu.fulopmark.boatdatacollectorbe.entity.WindData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface WindDataRepository extends JpaRepository<WindData, Long> {

    List<WindData> findAllByTimestampBetweenOrderByTimestampDesc(Instant start, Instant end);

}
