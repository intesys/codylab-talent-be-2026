package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.service.TrackingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackingController {

    private final TrackingService trackingService;

    // Spring inietterà automaticamente il Bean registrato nella Configuration
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/trackings")
    public List<Tracking> getAllTrackings() {
        return trackingService.findAll();
    }
}