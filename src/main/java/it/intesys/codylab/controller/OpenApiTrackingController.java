package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.TrackingControllerApi;
import it.intesys.codylab.controller.dto.ProblemApiDTO;
import it.intesys.codylab.controller.dto.TrackingApiDTO;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.mapper.TrackingMapper;
import it.intesys.codylab.service.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class OpenApiTrackingController implements TrackingControllerApi {

    private final TrackingService trackingService;
    private final TrackingMapper trackingMapper;

    public OpenApiTrackingController(TrackingService trackingService, TrackingMapper trackingMapper) {
        this.trackingService = trackingService;
        this.trackingMapper = trackingMapper;
    }

    @Override
    public ResponseEntity<List<TrackingApiDTO>> getAllTrackings() {
        return ResponseEntity.ok(trackingMapper.mapToApiDtoList(trackingService.findAll()));
    }

    @Override
    public ResponseEntity<TrackingApiDTO> getTrackingById(Long trackingId) {
        Optional<Tracking> trackingOptional = trackingService.findById(trackingId);
        if (trackingOptional.isPresent()) {
            return ResponseEntity.ok(trackingMapper.mapToApiDto(trackingOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TrackingApiDTO> createTracking(TrackingApiDTO trackingApiDTO) {
        Tracking trackingData = trackingMapper.mapToEntity(trackingApiDTO);
        long trackingId = trackingService.insertTrack(trackingData);
        return ResponseEntity.created(URI.create("/trackings/" + trackingId))
                .body(trackingMapper.mapToApiDto(trackingService.findById(trackingId).get()));
    }

    @Override
    public ResponseEntity<TrackingApiDTO> updateTracking(Long trackingId, TrackingApiDTO trackingApiDTO) {
        Optional<Tracking> trackingOptional = trackingService.findById(trackingId);
        if (trackingOptional.isPresent()) {
            Tracking trackingData = trackingMapper.mapToEntity(trackingApiDTO);
            boolean updated = trackingService.update(trackingId, trackingData);
            if (updated) {
                return ResponseEntity.ok(trackingMapper.mapToApiDto(trackingService.findById(trackingId).get()));
            }
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTracking(Long trackingId) {
        boolean deleted = trackingService.deleteById(trackingId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
