package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.WindData;
import hu.fulopmark.boatdatacollectorbe.repository.WindDataRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
public class WindDataController {

    private final WindDataRepository repository;


    @GetMapping("/v1/windDatas")
    @ApiOperation(value = "${api.summary.all}")
    List<WindData> all() {
        return repository.findAll();
    }

    @GetMapping("/v1/windDatasBetweenTimestamps")
    @ApiOperation(value = "${api.summary.betweenTimestamps}", notes = "${api.notes.betweenDescription}")
    List<WindData> betweenTimestamps(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {
        return repository.findAllByTimestampBetweenOrderByTimestampDesc(start, end);
    }

    @PostMapping("/v1/windDatas")
    @ApiOperation(value = "${api.summary.new}")
    WindData newWindData(@RequestBody WindData newWindData) {
        return repository.save(newWindData);
    }

    @GetMapping("/v1/windDatas/{id}")
    @ApiOperation(value = "${api.summary.one}")
    WindData one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WindData not found with id " + id));
    }

    @PutMapping("/v1/windDatas/{id}")
    @ApiOperation(value = "${api.summary.replace}")
    WindData replaceWindData(@RequestBody WindData newWindData, @PathVariable Long id) {

        return repository.findById(id)
                .map(windData -> {
                    windData.setDirection(newWindData.getDirection());
                    windData.setSpeed(newWindData.getSpeed());
                    windData.setModDate(Instant.now());
                    windData.setTimestamp(newWindData.getTimestamp());
                    return repository.save(windData);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newWindData.setId(id);
                    return repository.save(newWindData);
                });
    }

    @DeleteMapping("/windDatas/{id}")
    @ApiOperation(value = "${api.summary.delete}")
    void deleteWindData(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
