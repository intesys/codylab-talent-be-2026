package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.service.TrackingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public List<Tracking> getAll() {
        return trackingService.findAll();
    }
}