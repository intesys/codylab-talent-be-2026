package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.service.TrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public List<Tracking> findAll() {
        return trackingService.findAll();
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<Tracking> findById(@PathVariable long trackingId) {
        return trackingService.findById(trackingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tracking> insert(@RequestBody Tracking tracking) {
        long id = trackingService.insertTrack(tracking);
        tracking.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(tracking);
    }

    @PutMapping("/{trackingId}")
    public ResponseEntity<Void> update(@PathVariable long trackingId, @RequestBody Tracking tracking) {
        boolean updated = trackingService.update(trackingId, tracking);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{trackingId}")
    public ResponseEntity<Void> delete(@PathVariable long trackingId) {
        boolean deleted = trackingService.deleteById(trackingId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}