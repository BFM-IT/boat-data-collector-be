package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.SailingSession;
import hu.fulopmark.boatdatacollectorbe.repository.SailingSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SailingSessionController {

    private final SailingSessionRepository repository;

    @GetMapping("/sailingSessions")
    List<SailingSession> all() {
        return repository.findAll();
    }


    @PostMapping("/sailingSessions")
    SailingSession newSailingSession(@RequestBody SailingSession newSailingSession) {
        return repository.save(newSailingSession);
    }


    @GetMapping("/sailingSessions/{id}")
    SailingSession one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SailingSession not found with id " + id));
    }

    @PutMapping("/sailingSessions/{id}")
    SailingSession replaceSailingSession(@RequestBody SailingSession newSailingSession, @PathVariable Long id) {

        return repository.findById(id)
                .map(sailingSession -> {
                    sailingSession.setStart(newSailingSession.getStart());
                    sailingSession.setEnd(newSailingSession.getEnd());
                    sailingSession.setDescription(newSailingSession.getDescription());
                    sailingSession.setLocationPointSet(newSailingSession.getLocationPointSet());
                    sailingSession.setWindDataSet(newSailingSession.getWindDataSet());
                    return repository.save(sailingSession);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newSailingSession.setId(id);
                    return repository.save(newSailingSession);
                });
    }


    @DeleteMapping("/sailingSessions/{id}")
    void deleteSailingSession(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
