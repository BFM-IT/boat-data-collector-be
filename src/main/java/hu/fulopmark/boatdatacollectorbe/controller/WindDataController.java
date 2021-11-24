package hu.fulopmark.boatdatacollectorbe.controller;

import hu.fulopmark.boatdatacollectorbe.entity.WindData;
import hu.fulopmark.boatdatacollectorbe.repository.WindDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class WindDataController {

    private final WindDataRepository repository;


    @GetMapping("/windDatas")
    List<WindData> all() {
        return repository.findAll();
    }

    @PostMapping("/windDatas")
    WindData newWindData(@RequestBody WindData newWindData) {
        return repository.save(newWindData);
    }

    @GetMapping("/windDatas/{id}")
    WindData one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("WindData not found with id " + id));
    }

    @PutMapping("/windDatas/{id}")
    WindData replaceWindData(@RequestBody WindData newWindData, @PathVariable Long id) {

        return repository.findById(id)
                .map(windData -> {
                    windData.setDirection(newWindData.getDirection());
                    windData.setSpeed(newWindData.getSpeed());
                    windData.setSailingSession(newWindData.getSailingSession());
                    windData.setInserted(LocalDateTime.now());
                    windData.setTimestamp(newWindData.getTimestamp());
                    return repository.save(windData);
                })
                .orElseGet(() -> { // TODO exception kezelés
                    newWindData.setId(id);
                    return repository.save(newWindData);
                });
    }

    @DeleteMapping("/windDatas/{id}")
    void deleteWindData(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
