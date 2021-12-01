package hu.fulopmark.boatdatacollectorbe;

import hu.fulopmark.boatdatacollectorbe.repository.LocationPointRepository;
import hu.fulopmark.boatdatacollectorbe.repository.SailingSessionRepository;
import hu.fulopmark.boatdatacollectorbe.repository.WindDataRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
class BoatDataCollectorBeApplicationTests {


    private final SailingSessionRepository sailingSessionRepository;

    private final WindDataRepository windDataRepository;

    private final LocationPointRepository locationPointRepository;


    @Test
    void contextLoads() {
    }

}
