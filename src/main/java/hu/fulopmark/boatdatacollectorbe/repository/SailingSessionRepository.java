package hu.fulopmark.boatdatacollectorbe.repository;

import hu.fulopmark.boatdatacollectorbe.entity.SailingSession;
import hu.fulopmark.boatdatacollectorbe.entity.WindData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SailingSessionRepository extends JpaRepository<SailingSession, Long> {
}
