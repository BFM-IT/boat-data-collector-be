package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.LocationPoint;
import hu.fulopmark.boatdatacollectorbe.model.Distance;
import hu.fulopmark.boatdatacollectorbe.repository.LocationPointRepository;
import hu.fulopmark.boatdatacollectorbe.service.LocationPointService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class LocationPointController {

    private final LocationPointRepository repository;

    private final LocationPointService service;

    @GetMapping("/locationPoints")
    @ApiOperation(value = "${api.summary.all}")
    List<LocationPoint> all() {
        return repository.findAll();
    }


    @GetMapping("/locationPointsBetweenTimestamps")
    @ApiOperation(value = "${api.summary.betweenTimestamps}", notes = "${api.notes.betweenDescription}")
    List<LocationPoint> betweenTimestamps(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        return repository.findAllByTimestampBetweenOrderByTimestampDesc(start, end);
    }

    @GetMapping("/distanceBetweenTimestamps")
    @ApiOperation(value = "Calculate sum of distances between points in a time interval")
    Distance distanceBetweenPointsBetweenTimestamps(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        List<LocationPoint> locationPoints = repository.findAllByTimestampBetweenOrderByTimestampDesc(start, end);

        return new Distance(locationPoints.size(), service.sumDistanceBetweenPoints(locationPoints));


    }

    @PostMapping("/locationPoints")
    @ApiOperation(value = "${api.summary.new}")
    LocationPoint newLocationPoint(@RequestBody LocationPoint newLocationPoint) {
        return repository.save(newLocationPoint);
    }

    @GetMapping("/locationPoints/{id}")
    @ApiOperation(value = "${api.summary.one}")
    LocationPoint one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("LocationPoint not found with id " + id));
    }

    @PutMapping("/locationPoints/{id}")
    @ApiOperation(value = "${api.summary.replace}")
    LocationPoint replaceLocationPoint(@RequestBody LocationPoint newLocationPoint, @PathVariable Long id) {

        return repository.findById(id)
                .map(locationPoint -> {
                    locationPoint.setLatitude(newLocationPoint.getLatitude());
                    locationPoint.setLongitude(newLocationPoint.getLongitude());
                    locationPoint.setTimestamp(newLocationPoint.getTimestamp());
                    locationPoint.setModDate(Instant.now());
                    return repository.save(locationPoint);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newLocationPoint.setId(id);
                    return repository.save(newLocationPoint);
                });
    }

    @DeleteMapping("/locationPoints/{id}")
    @ApiOperation(value = "${api.summary.delete}")
    void deleteLocationPoint(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
