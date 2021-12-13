package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.SailingSession;
import hu.fulopmark.boatdatacollectorbe.repository.SailingSessionRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SailingSessionController {

    private final SailingSessionRepository repository;

    @GetMapping("/sailingSessions")
    @ApiOperation(value = "${api.summary.all}")
    List<SailingSession> all() {
        return repository.findAll();
    }


    @PostMapping("/sailingSessions")
    @ApiOperation(value = "${api.summary.new}")
    SailingSession newSailingSession(@RequestBody SailingSession newSailingSession) {
        return repository.save(newSailingSession);
    }


    @GetMapping("/sailingSessions/{id}")
    @ApiOperation(value = "${api.summary.one}")
    SailingSession one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SailingSession not found with id " + id));
    }

    @PutMapping("/sailingSessions/{id}")
    @ApiOperation(value = "${api.summary.replace}")
    SailingSession replaceSailingSession(@RequestBody SailingSession newSailingSession, @PathVariable Long id) {

        return repository.findById(id)
                .map(sailingSession -> {
                    sailingSession.setStartedAt(newSailingSession.getStartedAt());
                    sailingSession.setEndedAt(newSailingSession.getEndedAt());
                    sailingSession.setDescription(newSailingSession.getDescription());
                    sailingSession.setModDate(Instant.now());
                    return repository.save(sailingSession);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newSailingSession.setId(id);
                    return repository.save(newSailingSession);
                });
    }


    @DeleteMapping("/sailingSessions/{id}")
    @ApiOperation(value = "${api.summary.delete}")
    void deleteSailingSession(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
