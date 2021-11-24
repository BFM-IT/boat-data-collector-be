package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import hu.fulopmark.boatdatacollectorbe.repository.LocationPointRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@AllArgsConstructor
public class LocationPointController {

    private final LocationPointRepository repository;

    @GetMapping("/locationPoints")
    List<LocationPoint> all() {
        return repository.findAll();
    }

    @PostMapping("/locationPoints")
    LocationPoint newLocationPoint(@RequestBody LocationPoint newLocationPoint) {
        return repository.save(newLocationPoint);
    }

    @GetMapping("/locationPoints/{id}")
    LocationPoint one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("LocationPoint not found with id " + id));
    }

    @PutMapping("/locationPoints/{id}")
    LocationPoint replaceLocationPoint(@RequestBody LocationPoint newLocationPoint, @PathVariable Long id) {

        return repository.findById(id)
                .map(locationPoint -> {
                    locationPoint.setLatitude(newLocationPoint.getLatitude());
                    locationPoint.setLongitude(newLocationPoint.getLongitude());
                    locationPoint.setTimestamp(newLocationPoint.getTimestamp());
                    locationPoint.setInserted(LocalDateTime.now());
                    locationPoint.setSailingSession(newLocationPoint.getSailingSession());
                    return repository.save(locationPoint);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newLocationPoint.setId(id);
                    return repository.save(newLocationPoint);
                });
    }

    @DeleteMapping("/locationPoints/{id}")
    void deleteLocationPoint(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
